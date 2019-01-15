package com.matang28.restsqlexporter.core.sql.base;

import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConnection;
import com.matang28.restsqlexporter.utils.FLogger;
import com.matang28.restsqlexporter.utils.LabelFormat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;

public class JavaSqlConnection implements ISqlConnection {

    private final static FLogger logger = FLogger.getLogger(JavaSqlConnection.class);

    private Connection connection;

    public JavaSqlConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        this.getConnection().commit();
        this.getConnection().close();
    }

    @Override
    public ResultSet readQuery(String query) throws SQLException {
        return this.readQuery(query, Collections.emptyMap());
    }

    @Override
    public ResultSet readQuery(String query, Map<String, Object> queryArgs) throws SQLException {

        //logger.info("Running: {0}", query);

        Statement statement = this.getConnection().createStatement();

        String formattedQuery = LabelFormat.format(query, queryArgs);

        return statement.executeQuery(formattedQuery);
    }

    @Override
    public int updateQuery(String query) throws SQLException {
        return this.updateQuery(query, Collections.emptyMap());
    }

    @Override
    public int updateQuery(String query, Map<String, Object> queryArgs) throws SQLException {
        //logger.info("Running update query");

        Statement statement = this.getConnection().createStatement();

        String formattedQuery = LabelFormat.format(query, queryArgs);

        return (int) statement.executeLargeUpdate(formattedQuery);
    }
}
