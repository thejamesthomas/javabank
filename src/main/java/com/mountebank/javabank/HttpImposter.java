package com.mountebank.javabank;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class HttpImposter implements Imposter {
    private static final String HTTP = "http";

    @Getter
    private int port;
    @Getter
    private List<Stub> stubs = newArrayList();

    public static HttpImposter anImposter() {
        return new HttpImposter();
    }

    public static HttpImposter an(Class<HttpImposter> httpImposterClass) {
        return new HttpImposter();
    }

    public HttpImposter onPort(int port) {
        this.port = port;
        return this;
    }

    public HttpImposter addStub(Stub stub) {
        stubs.add(stub);
        return this;
    }

    public HttpImposter withStub(Stub stub) {
        stubs = newArrayList();
        stubs.add(stub);
        return this;
    }

    public Stub getStub(int index) {
        return stubs.get(index);
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", port);
        jsonObject.put("protocol", HTTP);
        if(!stubs.isEmpty()) {
            jsonObject.put("stubs", new JSONArray().addAll(stubs));
        }
        return jsonObject;
    }

    public String toString() {
        return toJSON().toJSONString();
    }
}
