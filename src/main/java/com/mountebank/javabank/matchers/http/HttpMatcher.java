package com.mountebank.javabank.matchers.http;

import com.mountebank.javabank.matchers.Matcher;

import java.util.Map;

public class HttpMatcher extends Matcher {

    private static final String PATH = "path";
    private static final String METHOD = "method";
    private static final String QUERY = "query";
    private static final String HEADERS = "headers";
    private static final String REQUEST_FROM = "requestFrom";
    private static final String BODY = "body";

    public HttpMatcher withType(HttpMatcherType type) {
        withType(type.getValue());
        return this;
    }

    public HttpMatcher withPath(String path) {
        addEntry(PATH, path);
        return this;
    }

    public HttpMatcher withMethod(String method) {
        addEntry(METHOD, method);
        return this;
    }

    public HttpMatcher withQueryParameters(Map<String, String> parameters) {
        addEntry(QUERY, parameters);
        return this;
    }

    public HttpMatcher addQueryParameter(String name, String value) {
        addMapEntry(QUERY, name, value);
        return this;
    }

    public HttpMatcher addHeader(String name, String value) {
        addMapEntry(HEADERS, name, value);
        return this;
    }

    public HttpMatcher withRequestFrom(String host, String port) {
        addEntry(REQUEST_FROM, host + ":" + port);
        return this;
    }

    public HttpMatcher withBody(String body) {
        addEntry(BODY, body);
        return this;
    }

    public String getPath() {
        return (String) getEntry(PATH);
    }

    public String getMethod() {
        return (String) getEntry(METHOD);
    }

    public String getRequestFrom() {
        return (String) getEntry(REQUEST_FROM);
    }

    public String getBody() {
        return (String) getEntry(BODY);
    }

    public Map<String, String> getQueryParameters() {
        return (Map<String, String>) getEntry(QUERY);
    }

    public String getQueryParameter(String parameter) {
        return getQueryParameters().get(parameter);
    }

    public Map<String, String> getHeaders() {
        return (Map<String, String>) getEntry(HEADERS);
    }

    public String getHeader(String name) {
        return getHeaders().get(name);
    }
}
