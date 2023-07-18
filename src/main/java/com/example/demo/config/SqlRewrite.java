package com.example.demo.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlRewrite {
    private MappedStatement mappedStatement;
    private Object parameterObject;
    private BoundSql boundSql;
    private Configuration configuration;
    private final TypeHandlerRegistry typeHandlerRegistry;
    private Logger logger = LoggerFactory.getLogger(com.example.demo.config.SqlRewrite.class);

    public SqlRewrite(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
    }

    public String rewriteSQL(String originalSql, TableSplit tableSplit) {
        Object rawVal = this.getFieldValue(tableSplit.shardBy());
        if (rawVal == null) {
            this.logger.error("split error, split key is null！ originalSql={}, tableSplit={}", originalSql, tableSplit);
            return originalSql;
        } else if (!tableSplit.shardType().startsWith("%") && !tableSplit.shardType().startsWith("/") && !tableSplit.shardType().equals("range")) {
            this.logger.error("split error, unknown split mod originalSql={}, tableSplit={}", originalSql, tableSplit);
            return originalSql;
        } else {
            long splitVal = NumberUtils.toLong(tableSplit.shardType().substring(1));
            if ((tableSplit.shardType().startsWith("%") || tableSplit.shardType().startsWith("/")) && splitVal < 1L) {
                this.logger.error("split error, split type incorrect originalSql={}, tableSplit={}", originalSql, tableSplit);
                return originalSql;
            } else if (StringUtils.isBlank(tableSplit.tableName())) {
                this.logger.error("split error, split tablename blank originalSql={}, tableSplit={}", originalSql, tableSplit);
                return originalSql;
            } else {
                String newSql = originalSql;
                String[] var7 = tableSplit.tableName().split(",");
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String singleTableName = var7[var9];
                    if (!StringUtils.isBlank(singleTableName)) {
                        singleTableName = singleTableName.trim();
                        String newTableName = null;
                        if (tableSplit.shardType().startsWith("%")) {
                            newTableName = singleTableName + NumberUtils.toLong(rawVal.toString()) % splitVal;
                        } else if (tableSplit.shardType().startsWith("/")) {
                            long split = NumberUtils.toLong(rawVal.toString()) / splitVal;
                            String suffix = split == 0L ? "" : String.valueOf(split);
                            newTableName = singleTableName + suffix;
                        } else if (tableSplit.shardType().equals("range")) {
                            JSONArray shardRange = JSONArray.parseArray(tableSplit.shardRange());

                            for(int i = 0; i < shardRange.size(); ++i) {
                                JSONObject range = shardRange.getJSONObject(i);
                                long begin = range.getLong("begin");
                                long end = range.getLong("end");
                                String suffix = range.getString("suffix");
                                long val = NumberUtils.toLong(rawVal.toString());
                                if (val >= begin && (val <= end || end == -1L)) {
                                    newTableName = singleTableName + suffix;
                                    break;
                                }
                            }
                        }

                        if (newTableName == null) {
                            throw new StandardException("-1", "sql rewrite error");
                        }

                        newSql = StringUtils.replacePattern(newSql, "\\s" + singleTableName + "\\s", " " + newTableName + " ");
                        newSql = StringUtils.replacePattern(newSql, singleTableName + "\\.", newTableName + ".");
                    }
                }

                return newSql;
            }
        }
    }

    public Object getFieldValue(String propertyName) {
        Object value;
        if (this.boundSql.hasAdditionalParameter(propertyName)) {
            value = this.boundSql.getAdditionalParameter(propertyName);
        } else if (this.parameterObject == null) {
            value = null;
        } else if (this.typeHandlerRegistry.hasTypeHandler(this.parameterObject.getClass())) {
            value = this.parameterObject;
        } else {
            MetaObject metaObject = this.configuration.newMetaObject(this.parameterObject);
            value = metaObject.getValue(propertyName);
        }

        return value;
    }

    public static void main(String[] args) {
        String a = "aaaa";
        String[] aArr = a.split(",");
        System.out.println(aArr);
    }
}