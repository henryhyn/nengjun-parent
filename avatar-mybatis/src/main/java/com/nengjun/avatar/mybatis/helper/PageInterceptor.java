package com.nengjun.avatar.mybatis.helper;

import com.nengjun.avatar.face.type.PageModel;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
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
        BoundSql boundSql = ms.getBoundSql(parameter);

        // 计算总的行数
        MappedStatement countMs = getCountMs(ms, boundSql);
        Long total = executeCount(executor, countMs, resultHandler);
        pageModel.setTotal(total);

        // 生成分页 SQL 语句
        args[MAPPED_STATEMENT_INDEX] = getPageMs(ms, boundSql, pageModel.getOffset(), pageModel.getPageSize());
        return invocation.proceed();
    }

    private MappedStatement getPageMs(MappedStatement ms, BoundSql boundSql, Integer offset, Integer pageSize) {
        String sql = boundSql.getSql();
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        Select select = (Select) stmt;
        PlainSelect countBody = ((PlainSelect) select.getSelectBody());
        countBody.setSelectItems(Collections.singletonList(new SelectExpressionItem(new Column("id"))));
        select.setSelectBody(countBody);
        String pageSql = String.format("%s inner join (%s limit %d, %d) b where a.id = b.id", sql, select, offset, pageSize);
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), new BoundSqlSqlSource(newBoundSql), ms.getSqlCommandType());
        builder.resultMaps(ms.getResultMaps());
        return builder.build();
    }

    private MappedStatement getCountMs(MappedStatement ms, BoundSql boundSql) {
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
        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        String msId = String.format("%sCount", ms.getId());
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), msId, new BoundSqlSqlSource(countBoundSql), ms.getSqlCommandType());
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, new ArrayList<ResultMapping>(0)).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        return builder.build();
    }

    private Long executeCount(Executor executor, MappedStatement countMs, ResultHandler resultHandler) {
        Object countResultList = null;
        try {
            countResultList = executor.query(countMs, null, RowBounds.DEFAULT, resultHandler);
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
