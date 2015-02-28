package com.mountebank.javabank.fluent;

import com.mountebank.javabank.core.Predicate;
import com.mountebank.javabank.fluent.matchers.http.HttpMatcherBuilder;
import com.mountebank.javabank.matchers.MatcherConflictException;
import com.mountebank.javabank.matchers.http.HttpMatcherType;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PredicateBuilder implements FluentBuilder {
    private StubBuilder parent;
    private List<HttpMatcherBuilder> childHttpMatcherBuilders = newArrayList();

    protected PredicateBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public HttpMatcherBuilder equals() {
        return getHttpMatcherBuilder(HttpMatcherType.EQUALS);
    }

    public HttpMatcherBuilder deepEquals() {
        return getHttpMatcherBuilder(HttpMatcherType.DEEP_EQUALS);
    }

    public HttpMatcherBuilder contains() {
        return getHttpMatcherBuilder(HttpMatcherType.CONTAINS);
    }

    public HttpMatcherBuilder startsWith() {
        return getHttpMatcherBuilder(HttpMatcherType.STARTS_WITH);
    }

    public HttpMatcherBuilder endsWith() {
        return getHttpMatcherBuilder(HttpMatcherType.ENDS_WITH);
    }

    public HttpMatcherBuilder matches() {
        return getHttpMatcherBuilder(HttpMatcherType.MATCHES);
    }

    public HttpMatcherBuilder exists() {
        return getHttpMatcherBuilder(HttpMatcherType.EXISTS);
    }

    private HttpMatcherBuilder getHttpMatcherBuilder(HttpMatcherType type) {
        HttpMatcherBuilder httpMatcherBuilder = new HttpMatcherBuilder(this, type);
        childHttpMatcherBuilders.add(httpMatcherBuilder);
        return httpMatcherBuilder;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected Predicate build() {
        Predicate predicate = new Predicate();
        for(HttpMatcherBuilder httpMatcherBuilder : childHttpMatcherBuilders) {
            try {
                predicate.addMatcher(httpMatcherBuilder.build());
            } catch (MatcherConflictException e) {
                e.printStackTrace();
            }
        }

        return predicate;
    }
}
