package com.example.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.*;
import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class TableSplitInterceptor implements Interceptor{
    private static final Logger log = LoggerFactory.getLogger(com.example.demo.config.TableSplitInterceptor.class);
    private static final String tag = com.example.demo.config.TableSplitInterceptor.class.getName();
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    public TableSplitInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
        BoundSql boundSql = (BoundSql)metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        if (StringUtils.isNotBlank(originalSql)) {
            MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(className.length() + 1);
            TableSplit tableSplit = (TableSplit) CusAnnotationUtils.getAnnotationValueFromMethodOrClass(className, methodName, TableSplit.class);
            if (tableSplit != null) {
                SqlRewrite sqlRewrite = new SqlRewrite(mappedStatement, parameterObject, boundSql);
                String newSQL = sqlRewrite.rewriteSQL(originalSql, tableSplit);
                if (newSQL != null) {
                    metaStatementHandler.setValue("delegate.boundSql.sql", newSQL);
                }
            }
        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    public void setProperties(Properties properties) {
    }

    public static void main(String[] args) {
        String sql = "INSERT INTO user_fetch_feed\n    (user_id, user_fetch_feed.feed_id, from_which, fetch_count, create_time, update_time)\n    VALUES\n      (#{item.userId}, #{item.feedId}, #{item.fromWhich}, #{item.fetchCount}, NOW(), NOW())\n    ON DUPLICATE KEY UPDATE\n    fetch_count = fetch_count + 1,\n    update_time = NOW()";
        String newSql = StringUtils.replacePattern(sql, "\\suser_fetch_feed\\s", " user_fetch_feed123 ");
        newSql = StringUtils.replacePattern(newSql, "user_fetch_feed\\.", "user_fetch_feed123.");
        System.out.println(newSql);
    }
}
