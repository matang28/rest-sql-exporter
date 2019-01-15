package com.matang28.restsqlexporter.core.sql;

import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConfig;
import com.matang28.restsqlexporter.core.sql.interfaces.ISqlConnection;
import com.matang28.restsqlexporter.utils.FLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class SqlWrapper {

    private final static FLogger logger = FLogger.getLogger(SqlWrapper.class);

    public static ResultSet runReadQuery(ISqlConfig sqlConfig, String query, Map<String, Object> argsMap){
        ResultSet resultSet = null;
        ISqlConnection connection = null;
        try{
            connection = SqlConnectionFactory.createConnection(sqlConfig);
            resultSet = connection.readQuery(query, argsMap);

        }
        catch (Exception e){
            logger.error("Cannot run read query where query={0}. Failed with exception", e, query);
        }
        finally {
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Cannot close sql connection. Failed with exception", e);
                }
        }

        return resultSet;
    }

    public static ResultSet runReadQuery(ISqlConfig sqlConfig, String query){
        return runReadQuery(sqlConfig, query, Collections.emptyMap());
    }

    public static Integer runUpdateQuery(ISqlConfig sqlConfig, String query, Map<String, Object> argsMap){
        Integer result = 0;
        ISqlConnection connection = null;
        try{
            connection = SqlConnectionFactory.createConnection(sqlConfig);
            result = connection.updateQuery(query, argsMap);
        }
        catch (Exception e){
            logger.error("Cannot run read query where query={0}. Failed with exception", e, query);
        }
        finally {
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Cannot close sql connection. Failed with exception", e);
                }
        }

        return result;
    }

    public static Integer runUpdateQuery(ISqlConfig sqlConfig, String query){
        return runUpdateQuery(sqlConfig, query, Collections.emptyMap());
    }

}
