package org.mbtest.javabank.http.responses;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class Inject extends HashMap {
    public static final String INJECT = "inject";

    public Inject() {
        this.put(INJECT, "");
    }

    public Inject withFunction(String function) {
        this.put(INJECT, function);
        return this;
    }

    public String toString() {
        return getJSON().toJSONString();
    }

    public JSONObject getJSON() {
        return new JSONObject(this);
    }
}
