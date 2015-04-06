package org.mbtest.javabank.http.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubTest {
    @Test
    public void shouldAddAResponse() {
        Stub stub = new Stub()
                .withResponse(new Is());

        assertThat(stub.getResponses()).hasSize(1);
    }

    @Test
    public void shouldAddAnotherResponse() {
        Stub stub = new Stub()
                .withResponse(new Is());

        Is additionalResponse = new Is();
        stub.addResponse(additionalResponse);

        assertThat(stub.getResponses()).hasSize(2);
        assertThat(stub.getResponses()).contains(additionalResponse);
    }

    @Test
    public void shouldOverwriteExistingResponsesWhenUsingWithResponse() {
        Is responseToBeOverwritten = new Is().withStatusCode(400);
        Stub stub = new Stub()
                .withResponse(responseToBeOverwritten);

        Is additionalResponse = new Is();
        stub.withResponse(additionalResponse.withStatusCode(200));

        assertThat(stub.getResponses()).hasSize(1);
        assertThat(stub.getResponses()).doesNotContain(responseToBeOverwritten);
    }
}