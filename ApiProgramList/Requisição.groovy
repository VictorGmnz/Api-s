import br.com.singlepoint.forms.engine.*;    
import java.sql.SQLException;
import br.com.singlepoint.forms.engine.FormsException;
import com.google.gson.Gson;
import org.json.JSONObject;
import com.singlepoint.utils.net.request.JSONCompleteListener;
import com.singlepoint.utils.module.transactscope.*;
import com.singlepoint.utils.module.transactscope.hibernate.*;
import com.singlepoint.utils.*;
import org.hibernate.*;
import groovy.transform.*;
import org.json.JSONObject;
import com.google.gson.Gson;
import java.util.*

this.doMain();

def doMain() {

    def values = []

    values = this.searchPrograms();

    gson = new Gson();
    $json = gson.toJson(values); 
}

def searchPrograms(){

    def obj = [];

    sqlSearchAll = new StringBuilder();
    sqlSearchAll.append(" SELECT ID_PROGRAM, DS_PROGRAM, FK_ID_CUSTOMER_INSTANCES, NM_DATABASE_INSTANCES, \n");
    sqlSearchAll.append(" NM_USERNAME_INSTANCES, NM_CUSTOMER_INSTANCES \n");
    sqlSearchAll.append(" FROM CUSTOM_PROGRAM \n");
    sqlSearchAll.append(" WHERE 1 = 1");
    if(_json.id_program != null  && _json.id_program != "" ){
        Long idProgram = _json.id_program;
        sqlSearchAll.append("\n AND ID_PROGRAM = " + idProgram);
    }
    querySearchAll = session.createSQLQuery(sqlSearchAll.toString());
    searchAll = querySearchAll.list();

    searchAll.each{ search ->

        valueAllPrograms = new ProgramInfo(
            id_program:search[0],
            ds_program:search[1],
            cd_status:2,
            id_program_instances: 3,
            fk_id_customer_instances: search[2],
            nm_database_instances: search[3],
            nm_username_instances: search[4],
            nm_customer_instances:search[5],
            id_programuserid_h22:6,
            id_crm493_codcliente:7,
            nm_key:""     
        );

        obj.add(valueAllPrograms)

    }

    return obj
}

@ToString
class ProgramInfo{
    public Long id_program = null;
    public String ds_program = "";
    public Long cd_status = null;
    public Long id_program_instances = null;
    public Long fk_id_customer_instances = null;
    public String nm_database_instances = "";
    public String nm_username_instances = "";
    public String nm_customer_instances = "";
    public Long id_programuserid_h22 = null;
    public Long id_crm493_codcliente = null;
    public String nm_key = "";
} 