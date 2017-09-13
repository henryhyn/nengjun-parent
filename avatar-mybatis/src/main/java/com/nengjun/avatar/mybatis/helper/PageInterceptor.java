package com.nengjun.avatar.mybatis.helper;

import com.nengjun.avatar.face.type.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Henry on 2017/7/18.
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {
    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;
    private static int RESULT_HANDLER_INDEX = 3;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
        Object parameter = args[PARAMETER_INDEX];
        RowBounds rowBounds = (RowBounds) args[ROWBOUNDS_INDEX];
        ResultHandler resultHandler = (ResultHandler) args[RESULT_HANDLER_INDEX];
        if (!(parameter instanceof PageModel<?>)) {
            return invocation.proceed();
        }

        PageModel<?> pageModel = (PageModel<?>) parameter;
        Executor executor = (Executor) invocation.getTarget();
        BoundSql boundSql = ms.getBoundSql(null);

        BoundSql whereBoundSql = getWhereString(ms, boundSql, pageModel.getConditions());

        // 计算总的行数
        MappedStatement countMs = getCountMs(ms, whereBoundSql);
        Long total = executeCount(executor, countMs, whereBoundSql.getParameterObject(), resultHandler);
        pageModel.setTotal(total);

        // 生成分页 SQL 语句
        args[MAPPED_STATEMENT_INDEX] = getPageMs(ms, whereBoundSql, pageModel.getOrders(), pageModel.getOffset(), pageModel.getPageSize());
        args[PARAMETER_INDEX] = whereBoundSql.getParameterObject();
        return invocation.proceed();
    }

    private BoundSql getWhereString(MappedStatement ms, BoundSql boundSql, Map<String, Object> conditions) {
        if (conditions.isEmpty()) {
            return boundSql;
        }

        List<ParameterMapping> parameterMappings = new ArrayList<>();
        StringBuilder sb = new StringBuilder(boundSql.getSql());
        sb.append(" where");
        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            sb.append(String.format(" (%s) and", entry.getKey()));
            ParameterMapping parameterMapping = new ParameterMapping.Builder(ms.getConfiguration(), entry.getKey(), Object.class).build();
            parameterMappings.add(parameterMapping);
        }
        sb.delete(sb.length() - 4, sb.length());
        return new BoundSql(ms.getConfiguration(), sb.toString(), parameterMappings, conditions);
    }

    private MappedStatement getPageMs(MappedStatement ms, BoundSql boundSql, String orders, Integer offset, Integer pageSize) {
        String sql = boundSql.getSql();
        StringBuilder sb = new StringBuilder(sql);
        if (StringUtils.isNotBlank(orders)) {
            sb.append(String.format(" order by %s", orders.replace('.', ' ').replace(",", ", ")));
        }
        sb.append(String.format(" limit %d, %d", offset, pageSize));
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sb.toString(), boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), new BoundSqlSqlSource(newBoundSql), ms.getSqlCommandType());
        builder.resultMaps(ms.getResultMaps());
        return builder.build();
    }

    private MappedStatement getCountMs(MappedStatement ms, BoundSql boundSql) {
        String sql = boundSql.getSql();
        String countSql = sql.replaceAll("\r?\n", " ").replaceFirst("SELECT.+FROM", "SELECT count(1) FROM");
        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        String msId = String.format("%sCount", ms.getId());
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), msId, new BoundSqlSqlSource(countBoundSql), ms.getSqlCommandType());
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, new ArrayList<ResultMapping>(0)).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        return builder.build();
    }

    private Long executeCount(Executor executor, MappedStatement countMs, Object parameter, ResultHandler resultHandler) {
        Object countResultList = null;
        try {
            countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Long) ((List) countResultList).get(0);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object o) {
            return boundSql;
        }
    }
}
