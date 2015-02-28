package com.mountebank.javabank.core;

import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Stub {
    @Getter
    private List<Response> responses = newArrayList();
    @Getter
    private List<Predicate> predicates = newArrayList();

    public Stub withResponse(Response response) {
        responses.clear();
        responses.add(response);

        return this;
    }

    public Stub addResponse(Response response) {
        responses.add(response);

        return this;
    }

    public Stub addPredicate(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public Response getResponse(int index) {
        return responses.get(index);
    }

    public Predicate getPredicate(int index) {
        return predicates.get(index);
    }
}
