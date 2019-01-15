package com.matang28.restsqlexporter.core.sql.interfaces;

public interface ISqlConfig {

    String getConnectionName();

    String getUrl();

    String getUsername();

    String getPassword();

    Integer getMinPoolSize();

    Integer getMaxPoolSize();
}
