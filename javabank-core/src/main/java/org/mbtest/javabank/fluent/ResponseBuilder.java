package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.responses.Response;

public class ResponseBuilder implements FluentBuilder {
    private StubBuilder parent;
    private ResponseTypeBuilder builder;

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

    protected Response build() {
        if(builder != null) return builder.build();

        return new IsBuilder(this).build();
    }
}
