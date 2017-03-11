package org.mbtest.javabank.fluent;

import com.google.common.net.HttpHeaders;
import org.junit.Test;
import org.mbtest.javabank.http.core.Is;
import org.mbtest.javabank.http.core.Stub;
import org.mbtest.javabank.http.imposters.Imposter;
import org.mbtest.javabank.http.predicates.Predicate;
import org.mbtest.javabank.http.predicates.PredicateType;

import static org.assertj.core.api.Assertions.assertThat;

// @formatter:off
public class ImposterBuilderTest {
    @Test
    public void shouldBuildAnHttpImposterWithTheCorrectPort() {
        Imposter imposter = ImposterBuilder.anImposter().onPort(4545).build();

        assertThat(imposter.getPort()).isEqualTo(4545);
    }

    @Test
    public void shouldBuildAnHttpImposterWithAStub() {
        Imposter imposter = ImposterBuilder.anImposter()
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
        Imposter imposter = ImposterBuilder.anImposter()
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

        Is actualIs = (Is)imposter.getStub(0).getResponse(0);

        assertThat(actualIs.getBody()).isEqualTo(expectedBody);
        assertThat(actualIs.getStatusCode()).isEqualTo(expectedStatusCode);
        assertThat(actualIs.getHeaders().get(HttpHeaders.CONTENT_TYPE)).isEqualTo(expectedContentType);
    }

    @Test
    public void shouldBuildAnHttpImposterWithMultipleResponses() {
        Imposter imposter = ImposterBuilder.anImposter()
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
        Imposter imposter = ImposterBuilder.anImposter()
            .stub()
                .predicate()
                    .equals()
                        .method(expectedValue)
                    .end()
                .end()
            .end()
        .build();

        Predicate predicate = imposter.getStub(0).getPredicate(0);

        assertThat(predicate.getMethod()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldBuildAnHttpImposterWithMultipleMatchers() {
        Imposter imposter = ImposterBuilder.anImposter()
            .stub()
                .predicate()
                    .equals().body("test").end()
                    .contains().path("testing").end()
                    .startsWith().query("one","two").end()
                .end()
            .end()
        .build();

        Stub stub = imposter.getStub(0);
        Predicate firstPredicate = stub.getPredicate(0);
        Predicate secondPredicate = stub.getPredicate(1);
        Predicate thirdPredicate = stub.getPredicate(2);

        assertThat(firstPredicate.getType()).isEqualTo(PredicateType.EQUALS.getValue());
        assertThat(firstPredicate.getBody()).isEqualTo("test");

        assertThat(secondPredicate.getType()).isEqualTo(PredicateType.CONTAINS.getValue());
        assertThat(secondPredicate.getPath()).isEqualTo("testing");

        assertThat(thirdPredicate.getType()).isEqualTo(PredicateType.STARTS_WITH.getValue());
        assertThat(thirdPredicate.getQueryParameter("one")).isEqualTo("two");
    }

    @Test
    public void shouldBuildAnHttpImposterWithAPredicateWithMultipleCriteria() {
        Imposter imposter = ImposterBuilder.anImposter()
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

        Predicate predicate = imposter.getStub(0).getPredicate(0);

        assertThat(predicate.getBody()).isEqualTo("hello, world");
        assertThat(predicate.getMethod()).isEqualTo("POST");
        assertThat(predicate.getPath()).isEqualTo("/test");
        assertThat(predicate.getQueryParameter("first")).isEqualTo("1");
        assertThat(predicate.getHeader("Content-Type")).isEqualTo("text/plain");
    }
}
// @formatter:on