package com.mountebank.javabank.core;

import com.google.common.collect.ImmutableMap;
import com.mountebank.javabank.core.Predicate;
import com.mountebank.javabank.matchers.http.HttpMatcher;
import com.mountebank.javabank.matchers.MatcherConflictException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class PredicateTest {
    @Test
    public void shouldAddEqualsMatcher() throws MatcherConflictException {
        HttpMatcher httpMatcher = new HttpMatcher().withMethod("POST");
        Predicate predicate = new Predicate().addMatcher(httpMatcher);

        assertThat(predicate.getMatchers()).contains(httpMatcher);
    }

    @Test
    public void shouldAddASecondEqualsMatcher() throws MatcherConflictException {
        Predicate predicate = new Predicate().addMatcher(new HttpMatcher().withPath("test"));

        HttpMatcher secondMatcher = new HttpMatcher().withMethod("PUT");
        predicate.addMatcher(secondMatcher);

        assertThat(predicate.getMatchers()).contains(secondMatcher);
    }

    @Test
    public void shouldAddEqualsMatcherWithMapOfValues() throws MatcherConflictException {
        ImmutableMap<String, String> expectedParameters = ImmutableMap.of("first", "1", "second", "2");
        HttpMatcher httpMatcher = new HttpMatcher().withQueryParameters(expectedParameters);

        Predicate predicate = new Predicate().addMatcher(httpMatcher);

        HttpMatcher actualMatcher = (HttpMatcher) predicate.getMatchers().get(0);
        assertThat(actualMatcher.getQueryParameters()).isEqualTo(expectedParameters);
    }
}