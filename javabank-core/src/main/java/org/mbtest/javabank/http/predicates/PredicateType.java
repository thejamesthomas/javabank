package org.mbtest.javabank.http.predicates;

import lombok.Getter;

//@formatter:off
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
    
    public String getValue() {
    		return value;
    }
}
