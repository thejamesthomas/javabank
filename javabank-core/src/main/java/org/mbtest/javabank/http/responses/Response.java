package org.mbtest.javabank.http.responses;

import org.json.simple.JSONObject;

import java.util.HashMap;

public abstract class Response extends HashMap {

    public JSONObject getJSON() {
        return new JSONObject(this);
    }

    public String toString() {
        return getJSON().toJSONString();
    }
}
