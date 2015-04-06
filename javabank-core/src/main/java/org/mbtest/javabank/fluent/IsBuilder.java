package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.core.Is;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

public class IsBuilder implements FluentBuilder {
    private ResponseBuilder parent;
    private int statusCode = 200;
    private String body = "";
    private final HashMap<String, String> headers = newHashMap();

    public IsBuilder(ResponseBuilder responseBuilder) {
        this.parent = responseBuilder;
    }

    public IsBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public IsBuilder header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public IsBuilder body(String body) {
        this.body = body;
        return this;
    }

    public ResponseBuilder end() {
        return parent;
    }

    protected Is build() {
        Is is = new Is().withStatusCode(statusCode).withHeaders(headers).withBody(body);
        return is;
    }
}
