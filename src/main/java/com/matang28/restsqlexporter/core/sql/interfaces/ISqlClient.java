package com.matang28.restsqlexporter.core.sql.interfaces;

import java.sql.SQLException;

public interface ISqlClient {

    ISqlConfig getSqlConfig();

    ISqlConnection createConnection() throws ClassNotFoundException, SQLException;

}
