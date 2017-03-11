package org.mbtest.javabank.fluent;

import java.util.HashMap;

public class ResponseBuilder implements FluentBuilder {
    private StubBuilder parent;
    private AbstractResponseBuilder builder;

    protected ResponseBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public IsBuilder is() {
        builder = new IsBuilder(this);
        return (IsBuilder) builder;
    }

    public InjectBuilder inject() {
        builder = new InjectBuilder(this);
        return (InjectBuilder) builder;
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
