package com.mountebank.javabank.matchers;

import lombok.Getter;
import org.json.simple.JSONObject;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Matcher {
    @Getter
    private Map data = newHashMap();

    @Getter
    private String type;

    public Matcher withType(String name) {
        this.type = name;
        return this;
    }

    public Matcher addEntry(String key, String value) {
        data.put(key, value);
        return this;
    }

    public Matcher addEntry(String key, Map map) {
        data.put(key, map);
        return this;
    }

    public Matcher addMapEntry(String key, String name, String value) {
        if(!data.containsKey(key)) {
            data.put(key, newHashMap());
        }

        Map entryMap = (Map) data.get(key);
        entryMap.put(name, value);

        return this;
    }

    public Object getEntry(String key) {
        return data.get(key);
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(type, new JSONObject(data));

        return jsonObject.toJSONString();
    }
}
