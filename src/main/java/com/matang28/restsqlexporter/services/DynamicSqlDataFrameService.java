package com.matang28.restsqlexporter.services;

import com.matang28.restsqlexporter.results.DataFrame;
import com.matang28.restsqlexporter.services.core.SqlDataFrameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

@Component("DynamicSqlDataFrameService")
public class DynamicSqlDataFrameService extends SqlDataFrameService<HashMap<String, Object>> {

    public DynamicSqlDataFrameService(@Value("${sql.jdbc}") String jdbcString,
                                      @Value("${sql.username}") String username,
                                      @Value("${sql.password}") String password,
                                      @Value("${sql.pool.min}") Integer minPool,
                                      @Value("${sql.pool.max}") Integer maxPool) {
        super(jdbcString, username, password, minPool, maxPool);
    }

    @Override
    protected DataFrame<HashMap<String, Object>> convertResult(String name, ResultSet rs) throws SQLException {

        DataFrame<HashMap<String, Object>> result = new DataFrame<>(name);

        if(rs!=null){
            //For each row in the result set:
            while (rs.next()){

                //Get the result metadata
                ResultSetMetaData metaData = rs.getMetaData();

                //For each column in the result set:
                HashMap<String, Object> temp = new HashMap<>();
                for(int i=1;i<metaData.getColumnCount()+1;i++){
                    temp.put(metaData.getColumnLabel(i), rs.getObject(i));
                }

                //Add it to the result object:
                result.addResult(temp);
            }
        }

        return result;
    }
}
