package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.responses.Inject;

public class InjectBuilder extends AbstractResponseBuilder {
    private String function = "";

    public InjectBuilder(ResponseBuilder responseBuilder) {
        super(responseBuilder);
    }

    public InjectBuilder function(String function) {
        this.function = function;
        return this;
    }

    @Override
    protected Inject build() {
        return new Inject().withFunction(function);
    }
}
