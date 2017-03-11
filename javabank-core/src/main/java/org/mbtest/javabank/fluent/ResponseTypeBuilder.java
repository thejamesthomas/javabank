package org.mbtest.javabank.fluent;

import java.util.HashMap;

public abstract class ResponseTypeBuilder implements FluentBuilder {
    private ResponseBuilder parent;

    protected ResponseTypeBuilder(ResponseBuilder parent) {
        this.parent = parent;
    }

    @Override
    public ResponseBuilder end() {
        return parent;
    }

    abstract protected HashMap build();
}
