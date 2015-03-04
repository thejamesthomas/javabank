package com.mountebank.javabank.http.core;

import com.mountebank.javabank.http.predicates.Predicate;

import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Stub extends HashMap {

    public static final String RESPONSES = "responses";
    public static final String PREDICATES = "predicates";

    public Stub() {
        this.put(RESPONSES, newArrayList());
        this.put(PREDICATES, newArrayList());
    }

    public Stub withResponse(Is response) {
        getResponses().clear();
        getResponses().add(response);

        return this;
    }

    public Stub addResponse(Is response) {
        getResponses().add(response);

        return this;
    }

    public Stub addPredicates(List<Predicate> predicates) {
        getPredicates().addAll(predicates);
        return this;
    }

    public List<Is> getResponses() {
        return (List<Is>) this.get(RESPONSES);
    }

    public List<Predicate> getPredicates() {
        return (List<Predicate>) this.get(PREDICATES);
    }

    public Is getResponse(int index) {
        return getResponses().get(index);
    }

    public Predicate getPredicate(int index) {
        return getPredicates().get(index);
    }
}
