package com.mountebank.javabank;

import com.google.common.collect.ImmutableMap;
import com.mountebank.javabank.matchers.equals.EqualsMatcher;
import com.mountebank.javabank.matchers.equals.EqualsQueryMatcher;
import com.mountebank.javabank.matchers.MatcherConflictException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class PredicateTest {
    @Test
    public void shouldAddEqualsMatcher() throws MatcherConflictException {
        EqualsMatcher equalsMatcher = new EqualsMatcher("method", "POST");
        Predicate predicate = new Predicate().addMatcher(equalsMatcher);

        assertThat(predicate.getMatchers()).contains(equalsMatcher);
    }

    @Test
    public void shouldAddASecondEqualsMatcher() throws MatcherConflictException {
        Predicate predicate = new Predicate().addMatcher(new EqualsMatcher("path", "test"));

        EqualsMatcher secondMatcher = new EqualsMatcher("method", "PUT");
        predicate.addMatcher(secondMatcher);

        assertThat(predicate.getMatchers()).contains(secondMatcher);
    }

    @Test
    public void shouldThrowAnExceptionWhenAddingASecondEqualsMatcherWhenOneAlreadyExistsForThatType() {
        try {
            Predicate predicate = new Predicate().addMatcher(new EqualsMatcher("method", "alreadyExists"));

            predicate.addMatcher(new EqualsMatcher("method", "shouldNotBeAdded"));
            fail("MatcherConflictException expected because there already exists a 'method' EqualsMatcher.");
        } catch (MatcherConflictException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void shouldAddEqualsMatcherWithMapOfValues() throws MatcherConflictException {
        EqualsQueryMatcher equalsMatcher = new EqualsQueryMatcher().addQueryParameter("first", "1").addQueryParameter("second", "2");

        Predicate predicate = new Predicate().addMatcher(equalsMatcher);

        EqualsQueryMatcher actualMatcher = (EqualsQueryMatcher) predicate.getMatchers().get(0);
        assertThat(actualMatcher.getValue()).isEqualTo(ImmutableMap.of("first", "1", "second", "2"));
    }

    @Test
    public void shouldThrowAnExceptionWhenAddingASecondEqualsQueryMatcherWhenOneAlreadyExistsForThatQueryParameter() {
        try{
            Predicate predicate = new Predicate().addMatcher(new EqualsQueryMatcher().addQueryParameter("alreadyExists", "add"));

            predicate.addMatcher(new EqualsQueryMatcher().addQueryParameter("alreadyExists", "doNotAdd"));
            fail("MatcherConflictException expected because there already exists a matcher for the query parameter 'alreadyExists'.");
        } catch (MatcherConflictException e) {
            assertThat(e).isNotNull();
        }
    }
}