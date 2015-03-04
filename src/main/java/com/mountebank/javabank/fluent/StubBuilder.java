package com.mountebank.javabank.fluent;

import com.mountebank.javabank.http.core.Stub;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class StubBuilder implements FluentBuilder {
    private ImposterBuilder parent;
    private List<ResponseBuilder> childResponses = newArrayList();
    private List<PredicateTypeBuilder> childPredicates = newArrayList();

    protected StubBuilder(ImposterBuilder httpImposterBuilder) {
        this.parent = httpImposterBuilder;
    }

    public ResponseBuilder response() {
        ResponseBuilder childResponse = new ResponseBuilder(this);
        childResponses.add(childResponse);
        return childResponse;
    }

    public PredicateTypeBuilder predicate() {
        PredicateTypeBuilder childPredicate = new PredicateTypeBuilder(this);
        childPredicates.add(childPredicate);
        return childPredicate;
    }

    @Override
    public ImposterBuilder end() {
        return parent;
    }

    protected Stub build() {
        Stub stub = new Stub();
        for(ResponseBuilder childResponse : childResponses) {
            stub.addResponse(childResponse.build());
        }
        for(PredicateTypeBuilder childPredicate : childPredicates) {
            stub.addPredicates(childPredicate.build());
        }

        return stub;
    }
}
