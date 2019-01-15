package com.matang28.restsqlexporter.core.sql.base;

import com.matang28.restsqlexporter.core.sql.interfaces.ISqlClient;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConfig;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlClient implements ISqlClient {

    private ISqlConfig config;

    public SqlClient(ISqlConfig config) {
        this.config = config;
    }

    @Override
    public ISqlConfig getSqlConfig() {
        return this.config;
    }

    @Override
    public ISqlConnection createConnection() throws ClassNotFoundException, SQLException {

        //Load the mysql jdbc driver:
        Class.forName("com.mysql.jdbc.Driver");

        //Create a new connection using the driver:
        Connection connection = DriverManager.getConnection(
                this.getSqlConfig().getUrl(),
                this.getSqlConfig().getUsername(),
                this.getSqlConfig().getPassword()
        );

        return new SqlConnection(connection);
    }
}
