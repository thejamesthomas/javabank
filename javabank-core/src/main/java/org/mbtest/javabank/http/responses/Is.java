package org.mbtest.javabank.http.responses;

import com.google.common.net.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Maps.newHashMap;

public class Is extends Response {
    private static final String IS = "is";
    private static final String HEADERS = "headers";
    private static final String BODY = "body";
    private static final String STATUS_CODE = "statusCode";
    public static final String MODE = "_mode";

    private final Map<String, Object> data;
    private Map<String, String> headers;

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

    public Is withHeaders(Map<String, String> headers) {
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
