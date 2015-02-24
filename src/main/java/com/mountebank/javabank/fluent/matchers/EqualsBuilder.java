package com.mountebank.javabank.fluent.matchers;

import com.mountebank.javabank.fluent.FluentBuilder;
import com.mountebank.javabank.fluent.PredicateBuilder;
import com.mountebank.javabank.matchers.equals.EqualsMatcher;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

public class EqualsBuilder implements FluentBuilder {
    private PredicateBuilder parent;
    private String method;
    private String path;
    private String body;
    private String except;
    private boolean caseSensitive = false;
    private HashMap<String, String> parameters = newHashMap();

    public EqualsBuilder(PredicateBuilder predicateBuilder) {
        parent = predicateBuilder;
    }

    public EqualsBuilder method(String method) {
    this.method = method;
    return this;
}

    public EqualsBuilder path(String path) {
    this.path = path;
    return this;
}

    public EqualsBuilder query(String parameter, String value) {
    parameters.put(parameter, value);
    return this;
}

    public EqualsBuilder body(String body) {
    this.body = body;
    return this;
}

    public EqualsBuilder caseSensitive(boolean caseSensitive) {
    this.caseSensitive = caseSensitive;
    return this;
}

    public EqualsBuilder except(String except) {
        this.except = except;
        return this;
    }

    @Override
    public PredicateBuilder end() {
        return parent;
    }

    public EqualsMatcher build() {
        EqualsMatcher equalsMatcher = new EqualsMatcher();
        if(method != null) {
            equalsMatcher.withMethod(method);
        }
        return equalsMatcher;
    }
}
