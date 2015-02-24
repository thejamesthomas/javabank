package com.mountebank.javabank.fluent;

import com.mountebank.javabank.Response;

public class ResponseBuilder implements FluentBuilder {
    private StubBuilder parent;
    private IsBuilder isBuilder;

    protected ResponseBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public IsBuilder is() {
        isBuilder = new IsBuilder(this);
        return isBuilder;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected Response build() {
        Response response = new Response();
        if(isBuilder != null) response.withIs(isBuilder.build());

        return response;
    }
}
