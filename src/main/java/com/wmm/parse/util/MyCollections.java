package com.wmm.parse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCollections {

    public static <K,V> List<Map<K, V>> mixListListMaps(List<List<Map<K, V>>> listListMap1) {
        List<Map<K, V>> resListMap = new ArrayList<>();
        if (listListMap1 == null || listListMap1.size() == 0) return resListMap;
        for (List<Map<K, V>> listMap : listListMap1) {
            resListMap = mixListMap2ListMap(resListMap, listMap);
        }
        return resListMap;
    }

    //一个Map和List交集
    public static <K,V> List<Map<K, V>> mixListMap2Map(List<Map<K, V>> listMap1, Map<K, V> map) {
        List<Map<K, V>> resListMap = new ArrayList<>();
        if (listMap1 == null || listMap1.size() == 0) {
            resListMap.add(map);
            return resListMap;
        }
        if (map == null) {
            return listMap1;
        }
        for (Map<K, V> map1 : listMap1) {
            resListMap.add(mixMap2Map(map1, map));
        }
        return resListMap;

    }

    //两个List<Map>交叉集
    public static <K,V> List<Map<K, V>> mixListMap2ListMap(List<Map<K, V>> listMap1, List<Map<K, V>> listMap2) {
        List<Map<K, V>> resListMap = new ArrayList<>();
        if (listMap1 == null || listMap1.size() == 0) return listMap2;
        if (listMap2 == null || listMap2.size() == 0) return listMap1;
        for (Map<K, V> map1 : listMap1) {
            for (Map<K, V> map2 : listMap2) {
                resListMap.add(mixMap2Map(map1, map2));
            }
        }
        return resListMap;
    }

    //两个Map交集
    public static <K,V> Map<K, V> mixMap2Map(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> mapMix = new HashMap<>();
        if (map1 != null) mapMix.putAll(map1);
        if (map2 != null) mapMix.putAll(map2);
        return mapMix;
    }

}
