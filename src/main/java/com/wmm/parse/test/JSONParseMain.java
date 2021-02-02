package com.wmm.parse.test;

import com.wmm.parse.constants.JSONParseConstants;
import com.wmm.parse.context.JSONParseDefaultContext;
import com.wmm.parse.context.JSONParseContext;
import com.wmm.parse.jsonparse.JSON2Dat;
import com.wmm.parse.jsonparse.JSON2DatImpl;
import com.wmm.parse.jsonparse.JSONParse;
import com.wmm.parse.jsonparse.JSONParseDefaultImpl;
import com.wmm.parse.resource.YmlLoad;
import com.wmm.parse.util.MyIoUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class JSONParseMain {
    private static final Log log = LogFactory.getLog(JSONParseMain.class);
    public static void main(String[] args) throws IOException {
       /* String sourceJSON = "D:\\外部数据\\y银税通_发票核验\\nfp1_rep.txt";
//        String sourceJSON = "D:\\外部数据\\爱信诺测试数据\\SSD12019053109bfc610ORI.xml";
        String jsonStr = MyIoUtils.read(sourceJSON);
        log.info("source json:"+jsonStr);
        Set<String> tags = new HashSet<String>(){
            {
//                add(JSONParseConstants.JSON_ROOT);// 根标签
//                add( "response");
//                add("response.items");
                add( "response");
                add("response.items");
            }
        };
        Map<String, List<Map<String, String>>> res = parseJSONParse(JSONObject.fromObject(jsonStr).toString(),tags);
        log.info(JSONObject.fromObject(res));*/
        parseJson();




    }


    static void parseJson(){
        Map<String,List<String>> tabCols = YmlLoad.loadDatConfig(JSONParseMain.class.getClassLoader().getResource("nfp1.yml").getFile());
        String sourceJSON = "D:\\外部数据\\y银税通_发票核验\\nfp1_rep.txt";
        try {
            String jsonStr = MyIoUtils.read(sourceJSON);
            JSON2Dat json2Dat = new  JSON2DatImpl(tabCols);
            Map<String,String> datMap =  json2Dat.json2Dat(JSONObject.fromObject(jsonStr));
            log.info("JSON parse dat Success:"+JSONObject.fromObject(datMap));
        } catch (IOException e) {
            log.error(e);
        }
    }



   /* static Map<String, List<Map<String, String>>> parseJSONParse(String jsonStr, Set<String> tags){
//        JSONParse jsonParse = new JSONParseDatImpl();
        JSONParse jsonParse = new JSONParseDefaultImpl();
        JSON json = JSONObject.fromObject(jsonStr);
        JSONParseContext context = new JSONParseDefaultContext(tags);
        jsonParse.parseJson(context,json, JSONParseConstants.JSON_ROOT);
        log.info("JSON parse Success:"+JSONObject.fromObject(context.getResults()));
        Map<String, List<Map<String, String>>> convertDatResults =  context.covertResults();
        log.info("convert:"+JSONObject.fromObject(convertDatResults));
        return convertDatResults;
    }*/
}
