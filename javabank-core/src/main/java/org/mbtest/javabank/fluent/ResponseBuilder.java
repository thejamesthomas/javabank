package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.core.Is;

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

    protected Is build() {
        if(isBuilder != null) return isBuilder.build();

        return new IsBuilder(this).build();
    }
}
