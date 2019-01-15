package com.matang28.restsqlexporter.core.sql;

import com.matang28.restsqlexporter.utils.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.StringJoiner;

public class QueryBuilder {

    private String select;
    private String condition = "";
    private String groupBy = "";
    private String orderBy = "";
    private String limit = "";

    public QueryBuilder(String tableName, List<String> fields) {
        this.select = formatSelect(tableName, fields);
    }

    public QueryBuilder addCondition(String condition){

        if(StringUtils.notEmpty(condition))
            this.condition = MessageFormat.format("WHERE {0}", condition);

        return this;
    }

    public QueryBuilder addGroupByColumns(List<String> fields){

        if(null!=fields && fields.size()>0)
            this.groupBy = formatGroupBy(fields);

        return this;
    }

    public QueryBuilder addOrderByColumns(List<String> fields){

        if(null!=fields && fields.size()>0)
            this.orderBy = formatOrderBy(fields);

        return this;
    }

    public QueryBuilder addLimit(Integer limit){

        if(null!=limit)
            this.limit = formatLimit(limit);

        return this;
    }

    @Override
    public String toString() {
        return StringUtils.joinNonEmpty(" ", this.select, this.condition, this.groupBy, this.orderBy, this.limit, ";");
    }

    private static String formatSelect(String tableName, List<String> fields){
        return MessageFormat.format(
                "SELECT {0} FROM {1}",
                reduce(fields, "", "", ","),
                tableName
        );
    }

    private static String formatGroupBy(List<String> fields){
        return MessageFormat.format("GROUP BY {0}", reduce(fields, "","",","));
    }

    private static String formatOrderBy(List<String> fields){
        return MessageFormat.format("ORDER BY {0}", reduce(fields, "", "", ","));
    }

    private static String formatLimit(Integer limit){
        return MessageFormat.format("LIMIT {0}", String.valueOf(limit));
    }

    private static String reduce(List<String> list, String prefix, String postfix, String delimiter){

        StringJoiner joiner = new StringJoiner(delimiter, prefix, postfix);

        list.forEach(joiner::add);

        return joiner.toString();
    }
}
