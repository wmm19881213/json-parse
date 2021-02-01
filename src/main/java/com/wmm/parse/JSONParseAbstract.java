package com.wmm.parse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public abstract class JSONParseAbstract implements JSONParse{

    public List<Map<String, Object>> parseJson(JSONParseContext context,  JSON json, String arrID,String tags){
         return this.parseJson(context,json,null,  arrID,tags);
    }

    public List<Map<String, Object>> parseJson(JSONParseContext context,  JSON json,String tags){
          return this.parseJson(context, json,null,  null,tags);
    }

    @Override
    public String convertJSONKey(Object tag) {
        return String.valueOf(tag);
    }

    @Override
    public String createJSONTag(Object tag, Object nextTag) {
        return convertJSONKey(tag) + JSONParseConstants.SYMBOL_SPOT + convertJSONKey(nextTag);
    }
}
