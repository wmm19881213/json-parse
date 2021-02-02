package com.wmm.parse.test;

import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class MyJSONTest {
    private static final Log log = LogFactory.getLog(MyJSONTest.class);
    @Test
    public void testJSONArrayString(){
        String jsonStr = "[\"apple\",\"peer\",\"banana\"]";
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        for(int i=0;i<jsonArray.size();i++){
            /*JSONObject json = jsonArray.getJSONObject(i);
            log.info("json:"+json);*/
            Object obj = jsonArray.get(i);
            log.info("json:"+obj);
        }
    }
}
