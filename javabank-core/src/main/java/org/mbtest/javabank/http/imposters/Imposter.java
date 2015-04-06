package org.mbtest.javabank.http.imposters;

import org.mbtest.javabank.http.core.Stub;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Imposter extends HashMap {
    private static final String PORT = "port";
    private static final String PROTOCOL = "protocol";
    private static final String STUBS = "stubs";

    public Imposter() {
        this.put(PROTOCOL, "http");
        this.put(STUBS, newArrayList());
    }

    public static Imposter fromJSON(JSONObject json) {
        Imposter imposter = new Imposter();
        imposter.putAll(json);

        return imposter;
    }

    public static Imposter anImposter() {
        return new Imposter();
    }

    public Imposter onPort(int port) {
        this.put(PORT, port);
        return this;
    }

    public Imposter addStub(Stub stub) {
        getStubs().add(stub);
        return this;
    }

    public Imposter withStub(Stub stub) {
        this.remove(STUBS);
        this.put(STUBS, newArrayList());
        addStub(stub);
        return this;
    }

    public List<Stub> getStubs() {
        return ((List) get(STUBS));
    }

    public Stub getStub(int index) {
        return getStubs().get(index);
    }

    public JSONObject toJSON() {
        return new JSONObject(this);
    }

    public String toString() {
        return toJSON().toJSONString();
    }

    public int getPort() {
        return Integer.valueOf(String.valueOf(get(PORT)));
    }
}
