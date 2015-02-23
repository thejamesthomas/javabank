package com.mountebank.javabank.matchers.equals;

import com.mountebank.javabank.matchers.Matcher;

public class EqualsMatcher implements Matcher {
    private String type;
    private String value;

    public EqualsMatcher(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
