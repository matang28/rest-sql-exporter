package com.matang28.restsqlexporter.services.core;

import com.matang28.restsqlexporter.core.sql.SqlConnectionFactory;
import com.matang28.restsqlexporter.core.sql.base.SqlConfig;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConfig;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConnection;
import com.matang28.restsqlexporter.requests.SqlTableRequest;
import com.matang28.restsqlexporter.results.DataFrame;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SqlDataFrameService<T> implements IDataFrameService<SqlTableRequest, T>{

    private final ISqlConfig config;

    public SqlDataFrameService(String jdbcString,
                               String username,
                               String password,
                               Integer minPool,
                               Integer maxPool) {

        this.config = new SqlConfig(jdbcString, username, password, minPool, maxPool);
    }

    protected abstract DataFrame<T> convertResult(String name, ResultSet rs) throws SQLException;

    @Override
    public DataFrame<T> exportData(SqlTableRequest query) throws SQLException {

        //Create the connection to the SQL server:
        try(ISqlConnection connection = SqlConnectionFactory.createConnection(config)){
            //On valid connection:
            if(connection!=null){

                //Run the query:
                ResultSet rs = connection.readQuery(query.toQueryString());

                //Convert the result set and return the data-frame:
                return this.convertResult(query.getTableName(), rs);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        throw new SQLException("Cannot create SQL connection");
    }
}
