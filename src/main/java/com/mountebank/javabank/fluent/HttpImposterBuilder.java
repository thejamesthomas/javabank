package com.mountebank.javabank.fluent;

import com.mountebank.javabank.HttpImposter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class HttpImposterBuilder {
    private int port;
    private List<StubBuilder> stubBuilders = newArrayList();

    public static HttpImposterBuilder anImposter() {
        return new HttpImposterBuilder();
    }

    public HttpImposterBuilder onPort(int port) {
        this.port = port;
        return this;
    }

    public StubBuilder stub() {
        StubBuilder child = new StubBuilder(this);
        stubBuilders.add(child);
        return child;
    }

    public HttpImposter build() {
        HttpImposter imposter = new HttpImposter()
                .onPort(port);
        for (StubBuilder stubBuilder : stubBuilders) {
            imposter.addStub(stubBuilder.build());
        }
        return imposter;
    }
}
