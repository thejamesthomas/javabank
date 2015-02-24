package com.mountebank.javabank;

import org.json.simple.JSONObject;

public interface Imposter {
    Imposter onPort(int port);
    JSONObject toJSON();
}
