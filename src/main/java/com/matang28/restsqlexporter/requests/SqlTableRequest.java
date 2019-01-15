package com.matang28.restsqlexporter.requests;

import com.matang28.restsqlexporter.core.sql.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class SqlTableRequest implements IQueryable{

    private final String tableName;

    private final List<String> fieldsList;

    private final String whereStatement;

    private final List<String> groupFields;

    private final List<String> orderFields;

    private final Integer maxResults;

    public SqlTableRequest(final String tableName, final List<String> fieldsList, final Integer limit) {
        this.tableName = tableName;
        this.fieldsList = fieldsList;
        this.whereStatement = null;
        this.groupFields = new ArrayList<String>();
        this.orderFields = new ArrayList<String>();
        this.maxResults = limit;
    }

    public SqlTableRequest(final String tableName, final List<String> fieldsList, final String whereStatement, final Integer maxResults) {
        this.tableName = tableName;
        this.fieldsList = fieldsList;
        this.whereStatement = whereStatement;
        this.maxResults = maxResults;
        this.groupFields = new ArrayList<String>();
        this.orderFields = new ArrayList<String>();
    }

    public SqlTableRequest(final String tableName, final List<String> fieldsList, final String whereStatement, final List<String> groupFields, final Integer maxResults) {
        this.tableName = tableName;
        this.fieldsList = fieldsList;
        this.whereStatement = whereStatement;
        this.groupFields = groupFields;
        this.orderFields = new ArrayList<String>();
        this.maxResults = maxResults;
    }

    public SqlTableRequest(final String tableName, final List<String> fieldsList, final String whereStatement, final List<String> groupFields, final List<String> orderFields, final Integer maxResults) {
        this.tableName = tableName;
        this.fieldsList = fieldsList;
        this.whereStatement = whereStatement;
        this.groupFields = groupFields;
        this.orderFields = orderFields;
        this.maxResults = maxResults;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getFieldsList() {
        return fieldsList;
    }

    public String getWhereStatement() {
        return whereStatement;
    }

    public List<String> getGroupFields() {
        return groupFields;
    }

    public List<String> getOrderFields() {
        return orderFields;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    @Override
    public String toQueryString() {

        return new QueryBuilder(this.tableName, this.fieldsList)
                .addCondition(whereStatement)
                .addGroupByColumns(groupFields)
                .addOrderByColumns(orderFields)
                .addLimit(maxResults)
                .toString();
    }
}
