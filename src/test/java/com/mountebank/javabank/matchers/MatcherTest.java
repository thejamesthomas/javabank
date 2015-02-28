package com.mountebank.javabank.matchers;

import com.google.common.collect.ImmutableMap;
import org.json.simple.JSONValue;
import org.junit.Test;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

public class MatcherTest {
    @Test
    public void shouldSetTheName() {
        Matcher matcher = new Matcher().withType("equals");

        assertThat(matcher.getType()).isEqualTo("equals");
    }

    @Test
    public void shouldAddEntries() {
        Matcher matcher = new Matcher().addEntry("method", "POST");

        assertThat(matcher.getEntry("method")).isEqualTo("POST");
    }

    @Test
    public void shouldAddAMap() {
        Matcher matcher = new Matcher().addEntry("query", ImmutableMap.of("first", "1", "second", "2"));

        assertThat(matcher.getEntry("query")).isEqualTo(ImmutableMap.of("first", "1", "second", "2"));
    }

    @Test
    public void shouldAddAMapEntry() {
        Matcher matcher = new Matcher().addMapEntry("query", "first", "1");

        assertThat(matcher.getEntry("query")).isEqualTo(ImmutableMap.of("first", "1"));
    }

    @Test
    public void shouldAddAnotherMapEntryToAnExistingMap() {
        Matcher matcher = new Matcher().addEntry("query", newHashMap(ImmutableMap.of("first", "1"))).addMapEntry("query", "second", "2");

        assertThat(matcher.getEntry("query")).isEqualTo(ImmutableMap.of("first", "1", "second", "2"));
    }

    @Test
    public void shouldSerializeToJSON() {
        Matcher matcher = new Matcher()
                .withType("equals")
                .addEntry("method", "POST")
                .addEntry("path", "/test")
                .addEntry("query", ImmutableMap.of("first", "1", "second", "2"));

        String expectedJson = "{\"equals\":{\"path\":\"" + "\\" + "/test\",\"method\":\"POST\",\"query\":{\"first\":\"1\",\"second\":\"2\"}}}";

        assertThat(JSONValue.parse(matcher.toString())).isEqualTo(JSONValue.parse(expectedJson));
    }
}