package com.wmm.parse.jsonparse;

import com.wmm.parse.context.JSONParseContext;
import net.sf.json.JSON;

import java.util.List;
import java.util.Map;

public interface JSON2Dat {
    Map<String,String> json2Dat(JSON json,String seperate,String lineSeperate);
    Map<String,String> json2Dat(JSON json);
    JSONParseContext getContext();
//    Map<String,String> convert2Dimensional(String seperate,String lineSeperate);
}
