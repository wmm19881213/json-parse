package com.wmm.parse;

import java.util.*;

/**
 * 记录JSONObject上下文
 */
public abstract class JSONParseAbstractContext implements JSONParseContext{

    private final Map<String, List<Map<String,Object>>> results = new HashMap<String, List<Map<String,Object>>>();

    /**
     * json标签对应的Set
     */
    private Set<String> tags;

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }


    public Map<String, List<Map<String, Object>>> getResults() {
        return results;
    }

    public boolean setResult(String tag, final Map<String, Object> result) {
        if(!tags.contains(tag)) return false;
        List<Map<String,Object>> res = this.getResults().get(tag);
        if(res == null){
            res = new ArrayList<Map<String, Object>>();
        }
        res.add(result);
        this.getResults().put(tag,res);
        return true;
    }

    public boolean setResult(String tag, List<Map<String, Object>> result) {

        if(!tags.contains(tag) || result == null) return false;
        List<Map<String,Object>> res = this.getResults().get(tag);
        if(res == null){
            res = result;
        }else{
            res.addAll(result);
        }
        this.getResults().put(tag,res);
        return true;
    }
}
