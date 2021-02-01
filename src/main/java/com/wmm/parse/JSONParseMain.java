package com.wmm.parse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.*;

public class JSONParseMain {
    private static final Log log = LogFactory.getLog(JSONParseMain.class);
    public static void main(String[] args) throws IOException {
        String sourceJSON = "D:\\外部数据\\y银税通_发票核验\\nfp1_rep.txt";
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
        Map<String, List<Map<String, String>>> res = parseJSON(JSONObject.fromObject(jsonStr).toString(),tags);
        log.info(JSONObject.fromObject(res));
    }


    static Map<String, List<Map<String, String>>> parseJSON(String jsonStr, Set<String> tags){
//        JSONParse jsonParse = new JSONParseDatImpl();
        JSONParse jsonParse = new JSONParseDatImpl();
        JSON json = JSONObject.fromObject(jsonStr);
        JSONParseContext context = new JSONObjectDatContext(tags);
        jsonParse.parseJson(context,json,JSONParseConstants.JSON_ROOT);
        log.info("JSON parse Success:"+JSONObject.fromObject(context.getResults()));
        Map<String, List<Map<String, String>>> convertDatResults =  context.covertResults();
        log.info("convert:"+JSONObject.fromObject(convertDatResults));
        return convertDatResults;
    }
}
