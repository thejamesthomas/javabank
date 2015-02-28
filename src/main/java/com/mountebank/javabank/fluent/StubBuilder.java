package com.mountebank.javabank.fluent;

import com.mountebank.javabank.core.Stub;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class StubBuilder implements FluentBuilder {
    private HttpImposterBuilder parent;
    private List<ResponseBuilder> childResponses = newArrayList();
    private List<PredicateBuilder> childPredicates = newArrayList();

    protected StubBuilder(HttpImposterBuilder httpImposterBuilder) {
        this.parent = httpImposterBuilder;
    }

    public ResponseBuilder response() {
        ResponseBuilder childResponse = new ResponseBuilder(this);
        childResponses.add(childResponse);
        return childResponse;
    }

    public PredicateBuilder predicate() {
        PredicateBuilder childPredicate = new PredicateBuilder(this);
        childPredicates.add(childPredicate);
        return childPredicate;
    }

    @Override
    public HttpImposterBuilder end() {
        return parent;
    }

    protected Stub build() {
        Stub stub = new Stub();
        for(ResponseBuilder childResponse : childResponses) {
            stub.addResponse(childResponse.build());
        }
        for(PredicateBuilder childPredicate : childPredicates) {
            stub.addPredicate(childPredicate.build());
        }

        return stub;
    }
}
