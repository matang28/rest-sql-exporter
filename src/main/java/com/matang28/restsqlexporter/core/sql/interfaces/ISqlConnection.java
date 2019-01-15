package com.matang28.restsqlexporter.core.sql.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ISqlConnection extends AutoCloseable{

    void close() throws SQLException;

    ResultSet readQuery(String query) throws SQLException;

    ResultSet readQuery(String query, Map<String, Object> queryArgs) throws SQLException;

    int updateQuery(String query) throws SQLException;

    int updateQuery(String query, Map<String, Object> queryArgs) throws SQLException;

}
