package com.nengjun.avatar.helper;

import com.nengjun.avatar.type.PageModel;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        MappedStatement ms = (MappedStatement) args[0];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        BoundSql boundSql = ms.getBoundSql(pageModel.getConditions());

        // 计算总的行数
        MappedStatement countMs = getCountMs(ms, ms.getId() + "Count");
        Long total = executeCount(executor, countMs, new Object(), boundSql, rowBounds, resultHandler);
        pageModel.setTotal(total);

        // 生成分页 SQL 语句
        return invocation.proceed();
    }

    private MappedStatement getCountMs(MappedStatement ms, String msId) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), msId, ms.getSqlSource(), ms.getSqlCommandType());
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, new ArrayList<ResultMapping>(0)).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        return builder.build();
    }

    private Long executeCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, RowBounds rowBounds, ResultHandler resultHandler) {
        String sql = boundSql.getSql();
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        Select select = (Select) stmt;
        PlainSelect countBody = ((PlainSelect) select.getSelectBody());
        countBody.setSelectItems(Collections.singletonList(new SelectExpressionItem(new Column("count(1)"))));
        select.setSelectBody(countBody);
        String countSql = select.toString();
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        Object countResultList = null;
        try {
            countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, null, countBoundSql);
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
}
