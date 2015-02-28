package com.mountebank.javabank.fluent.matchers.http;

import com.mountebank.javabank.fluent.FluentBuilder;
import com.mountebank.javabank.fluent.PredicateBuilder;
import com.mountebank.javabank.matchers.http.HttpMatcher;
import com.mountebank.javabank.matchers.http.HttpMatcherType;

public class HttpMatcherBuilder implements FluentBuilder {
    private PredicateBuilder parent;
    private final HttpMatcher _httpMatcher = new HttpMatcher();

    public HttpMatcherBuilder(PredicateBuilder predicateBuilder, HttpMatcherType type) {
        _httpMatcher.withType(type);
        parent = predicateBuilder;
    }

    public HttpMatcherBuilder method(String method) {
        _httpMatcher.withMethod(method);
        return this;
    }

    public HttpMatcherBuilder path(String path) {
        _httpMatcher.withPath(path);
        return this;
    }

    public HttpMatcherBuilder query(String parameter, String value) {
        _httpMatcher.addQueryParameter(parameter, value);
        return this;
    }

    public HttpMatcherBuilder body(String body) {
        _httpMatcher.withBody(body);
        return this;
    }

    public HttpMatcherBuilder header(String name, String value) {
        _httpMatcher.addHeader(name, value);
        return this;
    }

    @Override
    public PredicateBuilder end() {
        return parent;
    }

    public HttpMatcher build() {
        return _httpMatcher;
    }
}
