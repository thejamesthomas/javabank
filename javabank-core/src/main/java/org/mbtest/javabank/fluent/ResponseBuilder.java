package org.mbtest.javabank.fluent;

import java.util.HashMap;

public class ResponseBuilder implements FluentBuilder {
    private StubBuilder parent;
    private AbstractResponseBuilder builder;

    protected ResponseBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public IsBuilder is() {
        IsBuilder isBuilder = new IsBuilder(this);
        builder = isBuilder;
        return isBuilder;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected HashMap build() {
        if(builder != null) return builder.build();

        return new IsBuilder(this).build();
    }
}
