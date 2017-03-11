package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.responses.Response;

public abstract class ResponseTypeBuilder implements FluentBuilder {
    private ResponseBuilder parent;

    protected ResponseTypeBuilder(ResponseBuilder parent) {
        this.parent = parent;
    }

    @Override
    public ResponseBuilder end() {
        return parent;
    }

    abstract protected Response build();
}
