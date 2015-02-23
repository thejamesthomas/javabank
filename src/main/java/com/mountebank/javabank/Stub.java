package com.mountebank.javabank;

import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Stub {
    @Getter
    List<Response> responses = newArrayList();

    public Stub withResponse(Response response) {
        responses.clear();
        responses.add(response);

        return this;
    }

    public Stub addResponse(Response response) {
        responses.add(response);

        return this;
    }
}
