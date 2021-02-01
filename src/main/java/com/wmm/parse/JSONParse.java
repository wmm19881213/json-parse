package com.wmm.parse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface JSONParse {

     List<Map<String, Object>> parseJson(JSONParseContext context, JSON json, String parrArrID, String arrID, String tags);

     List<Map<String, Object>> parseJson(JSONParseContext context, JSON json,String arrID,String tags);

     List<Map<String, Object>> parseJson(JSONParseContext context, JSON json,String tags);

     //转换JSON key值
     String convertJSONKey(Object tag);
     //拼接表名的key值
     String createJSONTag(Object tag, Object nextTag );

}
