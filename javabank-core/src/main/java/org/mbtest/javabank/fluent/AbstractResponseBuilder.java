package org.mbtest.javabank.fluent;

import java.util.HashMap;

public abstract class AbstractResponseBuilder implements FluentBuilder {
    private ResponseBuilder parent;

    protected AbstractResponseBuilder(ResponseBuilder parent) {
        this.parent = parent;
    }

    @Override
    public ResponseBuilder end() {
        return parent;
    }

    abstract protected HashMap build();
}
