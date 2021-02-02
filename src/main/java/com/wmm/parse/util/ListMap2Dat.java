package com.wmm.parse.util;


import com.wmm.parse.constants.JSONParseConstants;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMap2Dat {

    public static Map<String,String> mapListMap2Dat(Map<String, List<Map<String, String>>> sourceMap, Map<String,List<String>> tabCols, String seperate,String lineSep){
        if(sourceMap == null || sourceMap.size() ==0)return null;
        if(tabCols == null || tabCols.size() == 0)return null;
        Map<String,String> result = new HashMap<>();

        for(String tabKey:tabCols.keySet()){
            String datStr = listMap2Dat(sourceMap.get(tabKey),tabCols.get(tabKey),seperate,lineSep);
            if(!StringUtils.isEmpty(datStr))result.put(tabKey,datStr);
        }

        return result;
    }

    private static String listMap2Dat(List<Map<String, String>> lisMap, List<String> cols, String seperate,String lineSep) {
        if(lisMap == null || lisMap.size() == 0)return null;
        if(cols == null || cols.size()==0)return null;
        StringBuffer resultSb = new StringBuffer();
        for(Map<String,String> map:lisMap){
            String datLine =  map2Dat(map,cols,seperate);
            if(StringUtils.isEmpty(datLine)) continue;
            resultSb.append(datLine).append(lineSep);
        }
        return resultSb.toString();
    }


    /**
     * 将map转换为String，若map为null或无值则返回null
     * @param map
     * @param cols
     * @param seperate
     * @return
     */
    private static String map2Dat(Map<String,String> map, List<String> cols, String seperate){
        if(map == null || map.keySet().size()==0) return null;
        if(cols == null  || cols.size() == 0) return null;
        StringBuffer result = new StringBuffer();
        for(String col:cols){
            result.append(String.valueOf(map.get(String.valueOf(col)))).append(String.valueOf(seperate));
        }
        return result.toString();
    }


}
