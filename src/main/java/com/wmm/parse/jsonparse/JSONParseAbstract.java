package com.wmm.parse.jsonparse;

import com.wmm.parse.constants.JSONParseConstants;
import com.wmm.parse.context.JSONParseContext;
import net.sf.json.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public abstract class JSONParseAbstract implements JSONParse {

    @Override
    public List<Map<String, Object>> parseJson(JSONParseContext context, JSON json) {
        return this.parseJson(context,json,null,  null,null);
    }

    public List<Map<String, Object>> parseJson(JSONParseContext context, JSON json, String arrID, String tags){
         return this.parseJson(context,json,null,  arrID,tags);
    }

    public List<Map<String, Object>> parseJson(JSONParseContext context,  JSON json,String tags){
          return this.parseJson(context, json,null,  null,tags);
    }

    @Override
    public String convertJSONKey(Object tag) {
        return tag == null?null:String.valueOf(tag);
    }

    @Override
    public String createJSONTag(Object tag, Object nextTag) {
        if(tag == null){
            return convertJSONKey(nextTag);
        }else{
            return convertJSONKey(tag) + JSONParseConstants.SYMBOL_SPOT + convertJSONKey(nextTag);
        }
    }
}
