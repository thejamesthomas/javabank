package com.mountebank.javabank;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Imposter {

    @Getter
    private int port;
    @Getter
    private String protocol;
    @Getter
    private List<Stub> stubs;

    public static Imposter anImposter() {
        return new Imposter();
    }

    public static Imposter anHttpImposter() {
        return Imposter.anImposter().withProtocol("http");
    }

    public Imposter withPort(int port) {
        this.port = port;
        return this;
    }

    public Imposter withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public Imposter addStub(Stub stub) {
        stubs.add(stub);
        return this;
    }

    public Imposter withStub(Stub stub) {
        stubs = newArrayList();
        stubs.add(stub);
        return this;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("protocol", protocol);
        jsonObject.put("port", port);
        if(stubs != null) {
            jsonObject.put("stubs", new JSONArray().addAll(stubs));
        }
        return jsonObject;
    }

    public String toString() {
        return toJSON().toJSONString();
    }
}
