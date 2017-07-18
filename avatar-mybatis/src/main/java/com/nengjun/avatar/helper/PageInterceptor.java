package com.nengjun.avatar.helper;

import com.nengjun.avatar.type.PageModel;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * Created by Henry on 2017/7/18.
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object obj = args[1];
        if (!(obj instanceof PageModel<?>)) {
            return invocation.proceed();
        }

        PageModel<?> pageModel = (PageModel<?>) obj;
        pageModel.setTotal(100);
        System.out.println("================================================");
        System.out.println(obj);
        System.out.println("================================================");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
