package com.matang28.restsqlexporter.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestResponse<T> {

    @JsonProperty("code")
    int code;

    @JsonProperty("data")
    T data;

    public RestResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
