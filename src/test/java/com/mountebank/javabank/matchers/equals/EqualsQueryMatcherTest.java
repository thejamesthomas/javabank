package com.mountebank.javabank.matchers.equals;

import com.mountebank.javabank.matchers.MatcherConflictException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class EqualsQueryMatcherTest {
    @Test
    public void shouldNotAllowADuplicateQueryParameter() {
        try{
            EqualsQueryMatcher equalsQueryMatcher = new EqualsQueryMatcher().addQueryParameter("alreadyExists", "addThis");

            equalsQueryMatcher.addQueryParameter("alreadyExists", "doNotAddThis");
            fail("MatcherConflictException expected because the query parameter 'alreadyExists' is already being matched on.");
        } catch(MatcherConflictException e) {
            assertThat(e).isNotNull();
        }
    }

}