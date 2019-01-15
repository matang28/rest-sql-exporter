package com.matang28.restsqlexporter.core.sql;

import com.matang28.restsqlexporter.core.sql.base.JavaSqlConnection;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConfig;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConnection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

public class SqlConnectionFactory {

    private static ConcurrentHashMap<String, HikariDataSource> dataSourceMap = new ConcurrentHashMap<>();

    private static void createPool(ISqlConfig sqlConfig){

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(sqlConfig.getUrl());
        config.setUsername(sqlConfig.getUsername());
        config.setPassword(sqlConfig.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionInitSql("SELECT 1");
        config.setLeakDetectionThreshold(60000);
        config.setMinimumIdle(sqlConfig.getMinPoolSize());
        config.setMaxLifetime(1800000);
        config.setIdleTimeout(600000);
        config.setMaximumPoolSize(sqlConfig.getMaxPoolSize());
        config.setAutoCommit(false);


        HikariDataSource dataSource = new HikariDataSource(config);

        dataSourceMap.put(sqlConfig.getConnectionName(), dataSource);

    }

    private static HikariDataSource getConnectionPool(ISqlConfig sqlConfig){

        if(!dataSourceMap.containsKey(sqlConfig.getConnectionName())){
            createPool(sqlConfig);
        }

        return dataSourceMap.get(sqlConfig.getConnectionName());
    }

    public static ISqlConnection createConnection(ISqlConfig sqlConfig) {

        try {
            Connection connection = getConnectionPool(sqlConfig).getConnection();
            return new JavaSqlConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
