package com.wmm.parse.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YmlLoad {
    private static final Log log = LogFactory.getLog(YmlLoad.class);
    public static Map<String, List<String>> loadDatConfig(String  datConfig) {
        Map<String, List<String>> result = new HashMap<>();
        Yaml yaml = new Yaml();
        InputStream  datConfigStream = null;
        try{
            datConfigStream = new FileInputStream(datConfig);
            result = yaml.loadAs(datConfigStream,new HashMap<String,List<String>>().getClass());
        }catch (FileNotFoundException e) {
            log.error("dat config load error:",e);
        }finally{
            if(datConfigStream!=null){
                try {
                    datConfigStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
