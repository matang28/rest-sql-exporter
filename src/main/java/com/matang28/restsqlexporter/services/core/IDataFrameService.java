package com.matang28.restsqlexporter.services.core;

import com.matang28.restsqlexporter.requests.IQueryable;
import com.matang28.restsqlexporter.results.DataFrame;

import java.sql.SQLException;

public interface IDataFrameService<IN extends IQueryable, OUT> {

    DataFrame<OUT> exportData(IN query) throws SQLException;

}
