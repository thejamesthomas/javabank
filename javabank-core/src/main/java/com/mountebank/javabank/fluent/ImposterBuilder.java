package com.mountebank.javabank.fluent;

import com.mountebank.javabank.http.imposters.Imposter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ImposterBuilder {
    private int port;
    private List<StubBuilder> stubBuilders = newArrayList();

    public static ImposterBuilder anImposter() {
        return new ImposterBuilder();
    }

    public ImposterBuilder onPort(int port) {
        this.port = port;
        return this;
    }

    public StubBuilder stub() {
        StubBuilder child = new StubBuilder(this);
        stubBuilders.add(child);
        return child;
    }

    public Imposter build() {
        Imposter imposter = new Imposter().onPort(port);
        for (StubBuilder stubBuilder : stubBuilders) {
            imposter.addStub(stubBuilder.build());
        }
        return imposter;
    }
}
