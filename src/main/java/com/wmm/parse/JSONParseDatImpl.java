package com.wmm.parse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.util.*;

public class JSONParseDatImpl extends JSONParseAbstract{

    @Override
    public List<Map<String, Object>>  parseJson(JSONParseContext context,  JSON json, String parrArrID, String arrID,String tag){
        List<Map<String, Object>>  result = null;
        //json
        if (json != null && !JSONUtils.isNull(json)) {
            if(json instanceof  JSONObject){
                result =  parseJSONObject(context,(JSONObject)json,parrArrID, arrID,tag);
            }else if(json instanceof JSONArray){
                result = parseJSONArray( context,(JSONArray)json,parrArrID,tag);
            }
        } else {
            return null;
        }
        return context.setResult(tag,result)?null:result;
    }

    private List<Map<String, Object>> parseJSONObject(JSONParseContext context,JSONObject jsonObj, String parrArrID, String arrID,String tag){
        final Map<String,Object> tagRes = new HashMap<String, Object>();//object可能是如下两种情况：String，List<Map<String, String>>,null
        Iterator<?> it = jsonObj.keys();
        while(it.hasNext()){
            Object jsonKey = it.next();
            Object jsonVal = jsonObj.get(jsonKey);
            //若value值是
            if(jsonVal instanceof JSON){
                tagRes.put(convertJSONKey(String.valueOf(jsonKey)),parseJson(context,(JSON)jsonVal,parrArrID,createJSONTag(tag,jsonKey.toString())));
            }else {
                tagRes.put(convertJSONKey(String.valueOf(jsonKey)),String.valueOf(jsonVal));//除JSON外全部转换成String
            }
        }
        tagRes.put(JSONParseConstants.PARR_ARR_ID,parrArrID);
        tagRes.put(JSONParseConstants.ARR_ID,arrID);
        boolean isSetResult =  context.setResult(tag,tagRes);
        if(!isSetResult){
            List<Map<String, Object>>  res =  new ArrayList<Map<String, Object>>();
            res.add(tagRes);
            return res;
        }else{
            return null;
        }
    }

    private List<Map<String, Object>> parseJSONArray(JSONParseContext context,JSONArray jsonArray, String parrArrID,final String tag){
        List<Map<String, Object>> results = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            final Object valObj = jsonArray.get(i);
            if(valObj instanceof JSON){//JSON
                final List<Map<String, Object>> tagRes = parseJson( context, (JSON)valObj,  parrArrID,  String.valueOf(i), tag);
                if(tagRes!=null)new HashMap<String, Object>(){{put(tag,tagRes);}};
            }else{//非JSON时直接转换成String
//                Map<String,Object> result = new HashMap<>();
                results.add(new HashMap<String, Object>(){{put(tag,String.valueOf(valObj));}});
            }
        }
        boolean isSetResult = context.setResult(createJSONTag(null,tag),results);
        if(isSetResult) return null;
        return results;
    }


    @Override
    public String convertJSONKey(Object tag) {
        return String.valueOf(tag);
    }

    @Override
    public String createJSONTag(Object tag, Object nextTag) {
        tag = convertJSONKey(tag);
        if(tag == null || JSONParseConstants.JSON_ROOT.equals(tag)){
            return convertJSONKey(nextTag);
        }
        return convertJSONKey(tag) + JSONParseConstants.SYMBOL_SPOT + convertJSONKey(nextTag);
    }

}
