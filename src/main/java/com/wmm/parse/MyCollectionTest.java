package com.wmm.parse;

import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public class MyCollectionTest {
    private static final Log log = LogFactory.getLog(MyCollectionTest.class);
    public static void main(String[] args){
        testMixlistListMap();
    }


    static void testMixlistListMap(){
        List<List<Map<String,String>>> listListMap = new ArrayList<>();
        for(int i=1;i<3;i++){
            listListMap.add(createListMap(i));
        }

        listListMap.add(new ArrayList<Map<String, String>>());
        listListMap.add(null);
        log.info(JSONArray.fromObject(listListMap));
        log.info(JSONArray.fromObject(MyCollections.mixListListMaps(listListMap)));
    }

    private static List<Map<String, String>> createListMap(final int i) {
        final List<Map<String, String>> listMap = new ArrayList<Map<String, String>>(){
            {
                for(int ct =0;ct<i;ct++){
                    Map<String,String> map = new HashMap<String,String>(){
                        {
                            put("name"+new Random().nextInt(i*100),"Jack"+i);
                            put("age"+new Random().nextInt(i*100),"2"+i);
                        }
                    };
                    add(map);
                }
            }
        };
        return listMap;
    }


}
