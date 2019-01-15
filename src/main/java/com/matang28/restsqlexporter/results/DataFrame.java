package com.matang28.restsqlexporter.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataFrame<T> {

    @JsonProperty("name")
    protected String name;

    @JsonProperty("results")
    protected List<T> results;

    public DataFrame(String name) {
        this.name = name;
        this.results = new ArrayList<T>();
    }

    public String getName() {
        return name;
    }

    public List<T>  getResults() {
        return results;
    }

    public void setResults(List<T>  results) {
        this.results = results;
    }

    public void addResult(T value){
        this.getResults().add(value);
    }
}
