package com.mountebank.javabank.fluent;

import com.google.common.net.HttpHeaders;
import com.mountebank.javabank.HttpImposter;
import com.mountebank.javabank.Is;
import com.mountebank.javabank.matchers.Matcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        String expectedType = "method";
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

        assertThat(matcher.getType()).isEqualTo(expectedType);
        assertThat(matcher.getValue()).isEqualTo(expectedValue);
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