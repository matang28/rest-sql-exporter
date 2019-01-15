package com.matang28.restsqlexporter.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableNameRequest {

    @JsonProperty("token")
    private String token;

    @JsonProperty("name")
    private String name;

    public TableNameRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
