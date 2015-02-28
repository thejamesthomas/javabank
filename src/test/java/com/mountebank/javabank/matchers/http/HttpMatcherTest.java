package com.mountebank.javabank.matchers.http;

import com.google.common.collect.ImmutableMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

import static com.mountebank.javabank.matchers.http.HttpMatcherType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpMatcherTest {
    @Test
    public void shouldSetTheDefaultNameToEquals() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher().withType(EQUALS);

        assertThat(equalsJsonMatcher.getType()).isEqualTo("equals");
    }

    @Test
    public void shouldSetThePath() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher().withPath("/test");

        assertThat(equalsJsonMatcher.getPath()).isEqualTo("/test");
    }

    @Test
    public void shouldSetTheMethod() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher().withMethod("POST");

        assertThat(equalsJsonMatcher.getMethod()).isEqualTo("POST");
    }

    @Test
    public void shouldSetTheQueryParameters() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher().addQueryParameter("first", "1");

        assertThat(equalsJsonMatcher.getQueryParameter("first")).isEqualTo("1");
    }

    @Test
    public void shouldGetTheQueryParameters() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher().addQueryParameter("first", "1").addQueryParameter("second", "2");

        assertThat(equalsJsonMatcher.getQueryParameters()).isEqualTo(ImmutableMap.of("first", "1", "second", "2"));
    }

    @Test
    public void shouldAddHeaders() {
        HttpMatcher httpMatcher = new HttpMatcher().addHeader("Accept", "text/plain").addHeader("Content-Type", "application/json");

        assertThat(httpMatcher.getHeaders()).isEqualTo(ImmutableMap.of("Accept", "text/plain", "Content-Type", "application/json"));
    }

    @Test
    public void shouldSetRequestFrom() {
        HttpMatcher httpMatcher = new HttpMatcher().withRequestFrom("127.0.0.1", "12345");

        assertThat(httpMatcher.getRequestFrom()).isEqualTo("127.0.0.1:12345");
    }

    @Test
    public void shouldSetBody() {
        HttpMatcher httpMatcher = new HttpMatcher().withBody("Hello World!");

        assertThat(httpMatcher.getBody()).isEqualTo("Hello World!");
    }

    @Test
    public void shouldSerializeToJSON() {
        HttpMatcher equalsJsonMatcher = new HttpMatcher()
                .withType(EQUALS)
                .withMethod("POST")
                .withPath("/testing")
                .withRequestFrom("127.0.0.1", "12345")
                .withBody("hello, world")
                .addQueryParameter("one", "1")
                .addQueryParameter("two", "2")
                .addHeader("Accept", "text/plain")
                .addHeader("Content-Type", "application/json");

        String expectedJson = "{\"equals\":{" +
                "\"path\":\"\\/testing\"," +
                "\"headers\":{\"Accept\":\"text\\/plain\",\"Content-Type\":\"application\\/json\"}," +
                "\"method\":\"POST\"," +
                "\"query\":{\"one\":\"1\",\"two\":\"2\"}," +
                "\"body\":\"hello, world\"," +
                "\"requestFrom\":\"127.0.0.1:12345\"}}";

        assertThat(JSONValue.parse(equalsJsonMatcher.toString())).isEqualTo(JSONValue.parse(expectedJson));
    }
}