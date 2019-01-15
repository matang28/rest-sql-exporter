package com.matang28.restsqlexporter.controllers;


import com.matang28.restsqlexporter.requests.RestSqlRequest;
import com.matang28.restsqlexporter.requests.TableNameRequest;
import com.matang28.restsqlexporter.results.DataFrame;
import com.matang28.restsqlexporter.results.RestResponse;

import java.util.HashMap;

public interface ITableExporterController {

    RestResponse<DataFrame<HashMap<String, Object>>> getResults(RestSqlRequest request);

    RestResponse<String> generateTableToken(TableNameRequest tableName);

}
