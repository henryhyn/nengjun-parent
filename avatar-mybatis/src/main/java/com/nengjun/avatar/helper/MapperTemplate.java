package com.nengjun.avatar.helper;

import com.nengjun.avatar.exception.HexException;
import com.nengjun.avatar.utils.lang.StringUtil;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 2017/7/14.
 */
public abstract class MapperTemplate {
    private static final XMLLanguageDriver languageDriver = new XMLLanguageDriver();
    protected Map<String, Method> methodMap = new HashMap<String, Method>();
    protected Map<String, Class<?>> entityClassMap = new HashMap<String, Class<?>>();
    protected Class<?> mapperClass;
    protected MapperHelper mapperHelper;

    public MapperTemplate(Class<?> mapperClass, MapperHelper mapperHelper) {
        this.mapperClass = mapperClass;
        this.mapperHelper = mapperHelper;
    }

    /**
     * 根据msId获取接口类
     *
     * @param msId
     * @return
     */
    public static Class<?> getMapperClass(String msId) {
        if (msId.indexOf(".") == -1) {
            throw new HexException(1000, "当前MappedStatement的id=" + msId + ",不符合MappedStatement的规则!");
        }
        String mapperClassStr = msId.substring(0, msId.lastIndexOf("."));
        try {
            return Class.forName(mapperClassStr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * 获取执行的方法名
     *
     * @param ms
     * @return
     */
    public static String getMethodName(MappedStatement ms) {
        return getMethodName(ms.getId());
    }

    /**
     * 获取执行的方法名
     *
     * @param msId
     * @return
     */
    public static String getMethodName(String msId) {
        return msId.substring(msId.lastIndexOf(".") + 1);
    }

    /**
     * 该方法仅仅用来初始化ProviderSqlSource
     *
     * @param record
     * @return
     */
    public String dynamicSQL(Object record) {
        return "dynamicSQL";
    }

    /**
     * 添加映射方法
     *
     * @param methodName
     * @param method
     */
    public void addMethodMap(String methodName, Method method) {
        methodMap.put(methodName, method);
    }


    /**
     * 是否支持该通用方法
     *
     * @param msId
     * @return
     */
    public boolean supportMethod(String msId) {
        Class<?> mapperClass = getMapperClass(msId);
        if (mapperClass != null && this.mapperClass.isAssignableFrom(mapperClass)) {
            String methodName = getMethodName(msId);
            return methodMap.get(methodName) != null;
        }
        return false;
    }

    /**
     * 重新设置SqlSource，同时判断如果是Jdbc3KeyGenerator，就设置为MultipleJdbc3KeyGenerator
     *
     * @param ms
     * @param sqlSource
     */
    protected void setSqlSource(MappedStatement ms, SqlSource sqlSource) {
        MetaObject msObject = SystemMetaObject.forObject(ms);
        msObject.setValue("sqlSource", sqlSource);
    }

    /**
     * 检查是否配置过缓存
     *
     * @param ms
     * @throws Exception
     */
    private void checkCache(MappedStatement ms) throws Exception {
        if (ms.getCache() == null) {
            String nameSpace = ms.getId().substring(0, ms.getId().lastIndexOf("."));
            Cache cache;
            try {
                //不存在的时候会抛出异常
                cache = ms.getConfiguration().getCache(nameSpace);
            } catch (IllegalArgumentException e) {
                return;
            }
            if (cache != null) {
                MetaObject metaObject = SystemMetaObject.forObject(ms);
                metaObject.setValue("cache", cache);
            }
        }
    }

    /**
     * 重新设置SqlSource
     *
     * @param ms
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public void setSqlSource(MappedStatement ms) throws Exception {
        if (this.mapperClass == getMapperClass(ms.getId())) {
            throw new HexException(1000, "请不要配置或扫描通用Mapper接口类：" + this.mapperClass);
        }
        Method method = methodMap.get(getMethodName(ms));
        try {
            //第一种，直接操作ms，不需要返回值
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(this, ms);
            }
            //第二种，返回SqlNode
            else if (SqlNode.class.isAssignableFrom(method.getReturnType())) {
                SqlNode sqlNode = (SqlNode) method.invoke(this, ms);
                DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(ms.getConfiguration(), sqlNode);
                setSqlSource(ms, dynamicSqlSource);
            }
            //第三种，返回xml形式的sql字符串
            else if (String.class.equals(method.getReturnType())) {
                String xmlSql = (String) method.invoke(this, ms);
                SqlSource sqlSource = createSqlSource(ms, xmlSql);
                //替换原有的SqlSource
                setSqlSource(ms, sqlSource);
            } else {
                throw new HexException(1000, "自定义Mapper方法返回类型错误,可选的返回类型为void,SqlNode,String三种!");
            }
            //cache
            checkCache(ms);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过xmlSql创建sqlSource
     *
     * @param ms
     * @param xmlSql
     * @return
     */
    public SqlSource createSqlSource(MappedStatement ms, String xmlSql) {
        return languageDriver.createSqlSource(ms.getConfiguration(), "<script>\n\t" + xmlSql + "</script>", null);
    }

    public Class<?> getEntityClass(MappedStatement ms) {
        String msId = ms.getId();
        if (entityClassMap.containsKey(msId)) {
            return entityClassMap.get(msId);
        } else {
            Class<?> mapperClass = getMapperClass(msId);
            Type[] types = mapperClass.getGenericInterfaces();
            for (Type type : types) {
                if (!(type instanceof ParameterizedType)) {
                    continue;
                }
                ParameterizedType t = (ParameterizedType) type;
                Class<?> returnType = (Class<?>) t.getActualTypeArguments()[0];
                entityClassMap.put(msId, returnType);
                return returnType;
            }
        }
        throw new HexException(1001, "无法获取 Mapper<T, PK> 泛型类型: " + msId);
    }

    protected String tableName(Class<?> entityClass) {
        return StringUtil.camelhumpToUnderline(entityClass.getSimpleName()).toLowerCase();
    }

    protected List<String> columnNameList(Class<?> entityClass) {
        List<String> columns = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            columns.add(field.getName());
        }
        return columns;
    }
}
