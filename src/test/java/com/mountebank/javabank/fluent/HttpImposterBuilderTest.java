package com.mountebank.javabank.fluent;

import com.google.common.net.HttpHeaders;
import com.mountebank.javabank.imposters.http.HttpImposter;
import com.mountebank.javabank.core.Is;
import com.mountebank.javabank.core.Predicate;
import com.mountebank.javabank.matchers.Matcher;
import com.mountebank.javabank.matchers.http.HttpMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// @formatter:off
public class HttpImposterBuilderTest {
    @Test
    public void shouldBuildAnHttpImposterWithTheCorrectPort() {
        HttpImposter imposter = HttpImposterBuilder.anImposter().onPort(4545).build();

        assertThat(imposter.getPort()).isEqualTo(4545);
    }

    @Test
    public void shouldBuildAnHttpImposterWithAStub() {
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub().end()
            .stub().end()
        .build();

        assertThat(imposter.getStubs()).hasSize(2);
    }

    @Test
    public void shouldBuildAnHttpImposterWithAResponse() {
        String expectedBody = "Hello World!";
        int expectedStatusCode = 201;
        String expectedContentType = "plain/text";
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .response()
                    .is()
                        .body(expectedBody)
                        .statusCode(expectedStatusCode)
                        .header(HttpHeaders.CONTENT_TYPE, expectedContentType)
                    .end()
                .end()
            .end()
        .build();

        Is actualIs = imposter.getStub(0).getResponse(0).getIs(0);

        assertThat(actualIs.getBody()).isEqualTo(expectedBody);
        assertThat(actualIs.getStatusCode()).isEqualTo(expectedStatusCode);
        assertThat(actualIs.getHeaders().get(HttpHeaders.CONTENT_TYPE)).isEqualTo(expectedContentType);
    }

    @Test
    public void shouldBuildAnHttpImposterWithMultipleResponses() {
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .response().end()
                .response().end()
            .end()
        .build();

        assertThat(imposter.getStubs().get(0).getResponses()).hasSize(2);
    }

    @Test
    public void shouldBuildAnHttpImposterWithAPredicate() {
        String expectedValue = "POST";
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .predicate()
                    .equals()
                        .method(expectedValue)
                    .end()
                .end()
            .end()
        .build();

        Matcher matcher = imposter.getStub(0).getPredicate(0).getMatcher(0);

        assertThat(matcher.getEntry("method")).isEqualTo(expectedValue);
    }

    @Test
    public void shouldBuildAnHttpImposterWithMultiplePredicates() {
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .predicate().end()
                .predicate().end()
            .end()
        .build();

        assertThat(imposter.getStubs().get(0).getPredicates()).hasSize(2);
    }

    @Test
    public void shouldBuildAnHttpImposterWithMultipleMatchers() {
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .predicate()
                    .equals().end()
                    .deepEquals().end()
                    .contains().end()
                    .startsWith().end()
                    .endsWith().end()
                    .matches().end()
                    .exists().end()
                .end()
            .end()
        .build();

        Predicate predicate = imposter.getStub(0).getPredicate(0);

        assertThat(predicate.getMatchers()).hasSize(7);
    }

    @Test
    public void shouldBuildAnHttpImposterWithAMatcherWithMultipleCriteria() {
        HttpImposter imposter = HttpImposterBuilder.anImposter()
            .stub()
                .predicate()
                    .equals()
                        .body("hello, world")
                        .method("POST")
                        .path("/test")
                        .query("first", "1")
                        .header("Content-Type", "text/plain")
                    .end()
                .end()
            .end()
        .build();

        HttpMatcher matcher = (HttpMatcher) imposter.getStub(0).getPredicate(0).getMatcher(0);

        assertThat(matcher.getBody()).isEqualTo("hello, world");
        assertThat(matcher.getMethod()).isEqualTo("POST");
        assertThat(matcher.getPath()).isEqualTo("/test");
        assertThat(matcher.getQueryParameter("first")).isEqualTo("1");
        assertThat(matcher.getHeader("Content-Type")).isEqualTo("text/plain");
    }

    @Test
    public void shouldBlah() {
        HttpImposter imposter = HttpImposterBuilder.anImposter().onPort(4545)
                .stub()
//                .response()
//                    .is()
//                        .statusCode(201)
//                        .header("Content-Type", "application/xml")
//                        .body("Hello World!")
//                    .end()
//                .end()
//                .response().end()
                .end()
                .stub()
//                .response().end()
//                .predicate()
//                    .equals()
//                        .method("POST")
//                        .path("/test")
//                        .query("first", "1")
//                        .query("second", "2")
//                        .body("hello")
//                        .caseSensitive(true)
//                        .except("!$")
//                    .end()
//                .end()
                .end()
                .build();
    }
}
// @formatter:on