package com.wmm.parse.jsonparse;

import com.wmm.parse.constants.JSONParseConstants;
import com.wmm.parse.context.JSONParseContext;
import com.wmm.parse.context.JSONParseDefaultContext;
import com.wmm.parse.util.ListMap2Dat;
import net.sf.json.JSON;

import java.util.List;
import java.util.Map;

/**
 * JSON 解析成dat二维表文件
 */
public  class JSON2DatImpl implements JSON2Dat{
    private JSONParseContext context;
    private Map<String, List<String>> tabCols;

    public JSON2DatImpl(Map<String, List<String>> tabCols) {
          this(null,tabCols);
    }

    public JSON2DatImpl(JSONParseContext context, Map<String, List<String>> tabCols){
        this.tabCols = tabCols;
        if(context == null){
            this.context = new JSONParseDefaultContext(tabCols.keySet());
        }else{
            this.context = context;
        }
    }

    @Override
    public Map<String, String> json2Dat(JSON json,String seperate,String lineSeperate) {
        if(seperate == null)seperate = JSONParseConstants.SYSBOL_SEP_DEFAULT;
        if(lineSeperate == null)lineSeperate=JSONParseConstants.SYSBOL_LINE_SEP_DEFAULT;
       JSONParseDefaultImpl jsonParse = new JSONParseDefaultImpl();
       jsonParse.parseJson(this.context,json,JSONParseConstants.JSON_ROOT);
       return convert2Dimensional(seperate,lineSeperate);
    }

    @Override
    public Map<String, String> json2Dat(JSON json) {
        return json2Dat(json,null,null);
    }

    @Override
    public JSONParseContext getContext() {
        return this.context;
    }

    private Map<String, String> convert2Dimensional(String seperate,String lineSeperate) {
        Map<String, List<Map<String, String>>> convertResults = this.context.covertResults();
        return ListMap2Dat.mapListMap2Dat(convertResults,tabCols,seperate,lineSeperate);
    }
}
