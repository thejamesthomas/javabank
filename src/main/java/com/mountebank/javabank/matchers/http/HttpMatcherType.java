package com.mountebank.javabank.matchers.http;

import lombok.Getter;

public enum HttpMatcherType {
    EQUALS("equals"),
    DEEP_EQUALS("deepEquals"),
    CONTAINS("contains"),
    STARTS_WITH("startsWith"),
    ENDS_WITH("endsWith"),
    MATCHES("matches"),
    EXISTS("exists"),
    NOT("not"),
    OR("or"),
    AND("and"),
    INJECT("inject");

    @Getter
    private String value;

    HttpMatcherType(String value) {

        this.value = value;
    }
}
