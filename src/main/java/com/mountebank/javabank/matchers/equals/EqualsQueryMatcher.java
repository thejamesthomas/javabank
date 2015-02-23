package com.mountebank.javabank.matchers.equals;

import com.mountebank.javabank.matchers.Matcher;
import com.mountebank.javabank.matchers.MatcherConflictException;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

public class EqualsQueryMatcher implements Matcher {

    private final HashMap values = newHashMap();

    @Override
    public String getType() {
        return "query";
    }

    @Override
    public HashMap getValue() {
        return values;
    }

    public EqualsQueryMatcher addQueryParameter(String name, String value) throws MatcherConflictException {
        if(values.containsKey(name)) {
            throw new MatcherConflictException();
        }
        values.put(name, value);
        return this;
    }
}
