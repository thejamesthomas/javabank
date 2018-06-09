package org.mbtest.javabank.http.core;

import org.mbtest.javabank.http.predicates.Predicate;
import org.mbtest.javabank.http.responses.Is;
import org.mbtest.javabank.http.responses.Response;

import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Stub extends HashMap {

    private static final String RESPONSES = "responses";
    private static final String PREDICATES = "predicates";

    public Stub() {
        this.put(RESPONSES, newArrayList());
        this.put(PREDICATES, newArrayList());
    }

    public Stub withResponse(Is response) {
        getResponses().clear();
        getResponses().add(response);

        return this;
    }

    public Stub addResponse(Response response) {
        getResponses().add(response);

        return this;
    }

    public Stub addPredicates(List<Predicate> predicates) {
        getPredicates().addAll(predicates);
        return this;
    }

    public List<Response> getResponses() {
        return (List<Response>) this.get(RESPONSES);
    }

    public List<Predicate> getPredicates() {
        return (List<Predicate>) this.get(PREDICATES);
    }

    public HashMap getResponse(int index) {
        return getResponses().get(index);
    }

    public Predicate getPredicate(int index) {
        return getPredicates().get(index);
    }
}
