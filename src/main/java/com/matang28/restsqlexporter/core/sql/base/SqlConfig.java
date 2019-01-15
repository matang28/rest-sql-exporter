package com.matang28.restsqlexporter.core.sql.base;

import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConfig;
import com.matang28.restsqlexporter.utils.PropertyFileReader;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class SqlConfig implements ISqlConfig {

    private String url;
    private String userName;
    private String password;
    private String connectionName = UUID.randomUUID().toString();

    private Integer minPool, maxPool;

    public SqlConfig(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.minPool = 15;
        this.maxPool = 30;
    }

    public SqlConfig(String url, String userName, String password, Integer minPool, Integer maxPool) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.minPool = minPool;
        this.maxPool = maxPool;
    }

    public SqlConfig(String url, String userName, String password, String connectionName) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.connectionName = connectionName;
        this.minPool = 15;
        this.maxPool = 30;
    }

    public SqlConfig(String url, String userName, String password, String connectionName, Integer minPool, Integer maxPool) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.connectionName = connectionName;
        this.minPool = minPool;
        this.maxPool = maxPool;
    }

    public static SqlConfig fromFile(String fileName) throws IOException {

        PropertyFileReader pfr = new PropertyFileReader();
        Properties properties = pfr.getPropValues(fileName);
        String url = properties.getProperty("hibernate.connection.url");
        String username = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");

        return new SqlConfig(url, username, password);
    }

    @Override
    public String getConnectionName() {
        return this.connectionName;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Integer getMinPoolSize() {
        return this.minPool;
    }

    @Override
    public Integer getMaxPoolSize() {
        return this.maxPool;
    }


}
