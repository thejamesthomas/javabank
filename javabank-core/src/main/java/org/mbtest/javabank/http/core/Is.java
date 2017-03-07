package org.mbtest.javabank.http.core;

import com.google.common.net.HttpHeaders;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Maps.newHashMap;

public class Is extends HashMap {
    public static final String IS = "is";
    public static final String HEADERS = "headers";
    public static final String BODY = "body";
    public static final String STATUS_CODE = "statusCode";
    public static final String MODE = "_mode";

    private final HashMap<String, Object> data;
    private HashMap<String, String> headers;

    public Is() {
        headers = newHashMap();
        headers.put(HttpHeaders.CONNECTION, "close");

        this.data = newHashMap();
        this.data.put(STATUS_CODE, 200);
        this.data.put(HEADERS, headers);

        this.put(IS, data);
        withStatusCode(HttpStatus.SC_OK);
        withBody("");
    }

    public Is withStatusCode(int statusCode) {
        this.data.put("statusCode", statusCode);
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
        this.data.put(BODY, body);
        return this;
    }

    public Is withMode(String mode){
        if(!Objects.isNull(mode)) {
            this.data.put(MODE, mode);
        }
        return this;
    }

    public String toString() {
        return toJSON().toJSONString();
    }

    private JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(this);
        return jsonObject;
    }

    public JSONObject getJSON() {
        return new JSONObject(this);
    }

    public Is withHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        this.data.put(HEADERS, headers);  // issue #5
        return this;
    }

    public int getStatusCode() {
        return Integer.valueOf(String.valueOf(this.data.get(STATUS_CODE)));
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return (String) this.data.get(BODY);
    }

    public String getMode() {
        return (String) this.data.get(MODE);
    }
}
