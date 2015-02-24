package com.mountebank.javabank;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

public class Is {
    @Getter
    private int statusCode = HttpStatus.SC_OK;

    @Getter
    private HashMap<String, String> headers = newHashMap();

    @Getter
    private String body = "";

    public Is() {
        headers.put(HttpHeaders.CONNECTION, "close");
    }

    public Is withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Is withHeader(String name, String value) {
        this.headers.clear();
        addHeader(name, value);
        return this;
    }

    public Is addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public Is withBody(String body) {
        this.body = body;
        return this;
    }

    public String toString() {
        return toJSON().toJSONString();
    }

    private JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    public JSONObject getJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", statusCode);
        jsonObject.put("headers", headers);
        jsonObject.put("body", body);

        return jsonObject;
    }

    public Is withHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }
}
