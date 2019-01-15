package com.matang28.restsqlexporter.controllers;

import com.matang28.restsqlexporter.requests.RestSqlRequest;
import com.matang28.restsqlexporter.requests.SqlTableRequest;
import com.matang28.restsqlexporter.requests.TableNameRequest;
import com.matang28.restsqlexporter.results.DataFrame;
import com.matang28.restsqlexporter.results.RestResponse;
import com.matang28.restsqlexporter.services.core.IDataFrameService;
import com.matang28.restsqlexporter.services.core.ISecuredTableService;
import com.matang28.restsqlexporter.utils.FLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class TableExporterController implements ITableExporterController{

    private IDataFrameService<SqlTableRequest, HashMap<String, Object>> dataService;
    private ISecuredTableService encryptionService;
    private String generateToken;

    @Autowired
    public TableExporterController(IDataFrameService<SqlTableRequest, HashMap<String, Object>> dataService,
                                   ISecuredTableService encryptionService,
                                   @Value("${auth.sql.gen}") String generateToken) {
        this.dataService = dataService;
        this.encryptionService = encryptionService;
        this.generateToken = generateToken;
    }

    @Override @RequestMapping(method = RequestMethod.POST, value = "/data/sql/export")
    public RestResponse<DataFrame<HashMap<String, Object>>> getResults(@RequestBody RestSqlRequest request) {

        try{
            //Convert the request to sql request:
            SqlTableRequest input = this.convertRequest(request);

            //Use the data service to get the results:
            DataFrame<HashMap<String, Object>> result = this.dataService.exportData(input);

            return new RestResponse<>(200, result);
        }
        catch (Exception e){
            FLogger.getLogger(TableExporterController.class).error("", e);
            return new RestResponse<>(500, null);
        }
    }

    @Override @RequestMapping(method = RequestMethod.POST, value = "/data/sql/generate")
    public RestResponse<String> generateTableToken(@RequestBody TableNameRequest request) {

        try{
            if(generateToken.equals(request.getToken())){
                String token = this.encryptionService.encrypt(request.getName());
                return new RestResponse<>(200, token);
            }
        }
        catch (Exception e){
            FLogger.getLogger(TableExporterController.class).error("", e);
        }

        return new RestResponse<>(500, null);
    }

    private SqlTableRequest convertRequest(RestSqlRequest request){
        return new SqlTableRequest(
                this.encryptionService.decrypt(request.getToken()),
                request.getFieldsList(),
                request.getWhereStatement(),
                request.getGroupFields(),
                request.getOrderFields(),
                request.getMaxResults()
        );
    }
}
