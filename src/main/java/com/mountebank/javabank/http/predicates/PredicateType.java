package com.mountebank.javabank.http.predicates;

import lombok.Getter;

public enum PredicateType {
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

    PredicateType(String value) {

        this.value = value;
    }
}
