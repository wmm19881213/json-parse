package com.wmm.parse.context;

import com.wmm.parse.util.MyCollections;

import java.util.*;

public class JSONParseDefaultContext extends JSONParseAbstractContext {
    public JSONParseDefaultContext(Set<String> tags) {
//        tags.add(JSONParseConstants.JSON_ROOT);//增加根标签
        this.setTags(tags);
    }

    @Override
    public Map<String, List<Map<String, String>>> covertResults() {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        for(String key:this.getResults().keySet()){
            List<Map<String,Object>> listMap = this.getResults().get(key);
            List<Map<String, String>> convertListMap = convertListMap2Dimensional(listMap);
            result.put(key,convertListMap);
        }
        return result;
    }



    //将List<Map<String, Object>> 转换成2维 表List<Map<String, String>>
    public static <K,V> List<Map<String,String>> convertListMap2Dimensional(List<Map<K, V>> listMap) {
        List<Map<String,String>> resListMap = new ArrayList<>();
        for(Map<K, V> map:listMap){
            List<Map<String, String>> map2D = convertMap2Dimensional(map);
            resListMap.addAll(map2D);
        }
        return resListMap;


    }

    /**
     * map的value值中含有List，想将转换为二维数据
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<Map<String, String>> convertMap2Dimensional(Map<K, V> map) {
        Map<String, String> resMap = new HashMap<>(); //接收Map中的非List数据
        List<List<Map<String,String>>> resListListMap = new ArrayList<>();
        for(K key:map.keySet()){
            V val = map.get(key);
            if(val instanceof  List){
                resListListMap.add(convertListMap2Dimensional((List<Map<K, V>>) val));
            }else{
                resMap.put(String.valueOf(key),String.valueOf(val));
            }
        }
        return MyCollections.mixListMap2Map(MyCollections.mixListListMaps(resListListMap),resMap);
    }

}
