package com.wmm.parse.context;

import java.util.List;
import java.util.Map;

public interface JSONParseContext {
    //获取机解析结果文件
    Map<String, List<Map<String,Object>>> getResults();
    //每一层的json解析的文件存放到结果集中
    boolean setResult(String tag, final Map<String, Object> result);
    boolean setResult(String tag, final List<Map<String, Object>> result);

    //将结果转换为二维形式
    Map<String,List<Map<String,String>>> covertResults();
}
