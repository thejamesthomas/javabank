package com.mountebank.javabank;

import com.mountebank.javabank.matchers.equals.EqualsMatcher;
import com.mountebank.javabank.matchers.equals.EqualsQueryMatcher;
import com.mountebank.javabank.matchers.Matcher;
import com.mountebank.javabank.matchers.MatcherConflictException;
import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Predicate {
    @Getter
    private List<Matcher> matchers = newArrayList();

    public Predicate addEqualsMatcher(String name, String value) throws MatcherConflictException {
        addMatcher(new EqualsMatcher(name, value));
        return this;
    }

    public Predicate addMatcher(Matcher matcherToAdd) throws MatcherConflictException {
        for(Matcher matcher : matchers) {
            if(matcher.getClass().equals(EqualsMatcher.class)) {
                validateEqualsMatcher((EqualsMatcher) matcherToAdd, (EqualsMatcher) matcher);
            }
            if(matcher.getClass().equals(EqualsQueryMatcher.class)) {
                validateEqualsQueryMatcher((EqualsQueryMatcher) matcherToAdd, (EqualsQueryMatcher) matcher);
            }
        }
        matchers.add(matcherToAdd);
        return this;
    }

    private void validateEqualsQueryMatcher(EqualsQueryMatcher matcherToAdd, EqualsQueryMatcher matcher) throws MatcherConflictException {
        for(Object existingKey : matcher.getValue().keySet()) {
            if (matcherToAdd.getValue().containsKey(existingKey)) {
                throw new MatcherConflictException();
            }
        }
    }

    private void validateEqualsMatcher(EqualsMatcher matcherToAdd, EqualsMatcher matcher) throws MatcherConflictException {
        if(matcher.getType().equals(matcherToAdd.getType())) {
            throw new MatcherConflictException();
        }
    }

    public Matcher getMatcher(int index) {
        return matchers.get(index);
    }
}
