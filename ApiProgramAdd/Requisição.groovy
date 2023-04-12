import br.com.singlepoint.forms.engine.*;    
import br.com.singlepoint.forms.engine.FormsException;
import com.google.gson.Gson;
import com.google.gson.Gson;
import com.singlepoint.utils.enums.web.servlet.RequestMethod;
import com.singlepoint.utils.net.request.JSONCompleteListener;
import com.singlepoint.utils.module.transactscope.*;
import com.singlepoint.utils.module.transactscope.hibernate.*;
import com.singlepoint.utils.*;
import org.json.JSONObject;
import org.hibernate.*;
import java.sql.SQLException;
import java.util.*
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import groovy.transform.*;

this.doMain();

def doMain() {

    JSONObject json = new JSONObject(_json.toString());

    def values = [];

    if(_json.toString() == "{}" || _json.toString() == ""){

        values = [cod:"11", erro: "Faltam parâmetros - favor informar os campos obrigatórios"]
        gson = new Gson();
        $json = gson.toJson(values);

        } 
    else if (json.keySet().contains("id_program_instances") != true){

        values = [cod: "12", erro:"id_program_instances não informado."]; 
    }

    else if (json.keySet().contains("ds_program") != true){

        values = [cod: "13", erro:"ds_program não informado."]; 
    }

    else if (json.keySet().contains("fl_status") != true){

        values = [cod: "14", erro: "fl_status não informado"];                
    }

    else if (_json.fl_status != 1 && _json.fl_status != 2){

        values = [cod: "15", erro: "fl_status deve ser 1 ou 2"]; 
    }

    else if (json.keySet().contains("fk_id_customer_instances") != true){

        values = [cod: "16", erro:"fk_id_customer_instances não informado."]; 
    }

    else if (json.keySet().contains("nm_database_instances") != true){

        values = [cod: "17", erro:"nm_database_instances não informado."]; 
    }

    else if (json.keySet().contains("nm_username_instances") != true){

        values = [cod: "18", erro:"nm_username_instances não informado."]; 
    }
    else if (json.keySet().contains("nm_customer_instances") != true){

        values = [cod: "19", erro:"nm_customer_instances não informado."]; 
    }
    else if (json.keySet().contains("fl_attachment_portal") != true){

        values = [cod: "20", erro:"fl_attachment_portal não informado."]; 
    }

    else{

        values = this.insertProgram();                   
    }
  
    gson = new Gson();
    $json = gson.toJson(values);   
                 
}

def insertProgram(){

    Long idProgInstancesLong = _json.id_program_instances;
    String dsProgramString = _json.ds_program;
    Long flStatusLong = _json.fl_status;
    Long flAttPortalLong = _json.fl_attachment_portal;

    Long fkIdCusInstancesLong = _json.fk_id_customer_instances;
    String nmDatabaseString = _json.nm_database_instances;
    String nmUsernameString = _json.nm_username_instances;
    String nmCustomerString = _json.nm_customer_instances;
    Date dtCreated  = new Date();

    sqlInsertProg= new StringBuilder();
    sqlInsertProg.append("\n INSERT INTO CUSTOM_PROGRAM(ID_PROGRAM,ID_PROGRAM_INSTANCES,");
    sqlInsertProg.append("\n DS_PROGRAM, CD_STATUS, DT_CREATED, FK_ID_CREATEDBY, FK_ID_CUSTOMER_INSTANCES, NM_DATABASE_INSTANCES, NM_USERNAME_INSTANCES, NM_CUSTOMER_INSTANCES, FL_ATTACHMENT_PORTAL)")
    sqlInsertProg.append("\n VALUES (sq_custom_program.NEXTVAL,?,?,?,?,441,?,?,?,?,?)");
    queryProg = session.createSQLQuery(sqlInsertProg.toString());
    queryProg.setParameter(0, idProgInstancesLong, Hibernate.LONG);
    queryProg.setParameter(1, dsProgramString, Hibernate.STRING);
    queryProg.setParameter(2, flStatusLong, Hibernate.LONG);
    queryProg.setParameter(3, dtCreated, Hibernate.DATE);
    queryProg.setParameter(4, fkIdCusInstancesLong, Hibernate.LONG);
    queryProg.setParameter(5, nmDatabaseString, Hibernate.STRING);
    queryProg.setParameter(6, nmUsernameString, Hibernate.STRING);
    queryProg.setParameter(7, nmCustomerString, Hibernate.STRING);
    queryProg.setParameter(8, flAttPortalLong, Hibernate.LONG);
    queryProg.executeUpdate();

    values = [cod: "10", mensagem: "Registro inserido com sucesso"]

    return values;
}