package com.matang28.restsqlexporter.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RestSqlRequest {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("token")
    private String token;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("fields")
    private List<String> fieldsList;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("condition")
    private String whereStatement;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("groups")
    private List<String> groupFields;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("ordering")
    private List<String> orderFields;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("limit")
    private Integer maxResults;

    public RestSqlRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<String> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public String getWhereStatement() {
        return whereStatement;
    }

    public void setWhereStatement(String whereStatement) {
        this.whereStatement = whereStatement;
    }

    public List<String> getGroupFields() {
        return groupFields;
    }

    public void setGroupFields(List<String> groupFields) {
        this.groupFields = groupFields;
    }

    public List<String> getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(List<String> orderFields) {
        this.orderFields = orderFields;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
}
