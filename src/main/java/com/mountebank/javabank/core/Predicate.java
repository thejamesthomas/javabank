package com.mountebank.javabank.core;

import com.mountebank.javabank.matchers.Matcher;
import com.mountebank.javabank.matchers.MatcherConflictException;
import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Predicate {
    @Getter
    private List<Matcher> matchers = newArrayList();

    public Predicate addMatcher(Matcher matcherToAdd) throws MatcherConflictException {
        for(Matcher matcher : matchers) {
//            matcher.checkForConflict(matcherToAdd);
        }
        matchers.add(matcherToAdd);
        return this;
    }

    public Matcher getMatcher(int index) {
        return matchers.get(index);
    }
}
