package com.mountebank.javabank.core;

import com.mountebank.javabank.core.Response;
import com.mountebank.javabank.core.Stub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubTest {
    @Test
    public void shouldAddAResponse() {
        Stub stub = new Stub()
                .withResponse(new Response());

        assertThat(stub.getResponses()).hasSize(1);
    }

    @Test
    public void shouldAddAnotherResponse() {
        Stub stub = new Stub()
                .withResponse(new Response());

        Response additionalResponse = new Response();
        stub.addResponse(additionalResponse);

        assertThat(stub.getResponses()).hasSize(2);
        assertThat(stub.getResponses()).contains(additionalResponse);
    }

    @Test
    public void shouldOverwriteExistingResponsesWhenUsingWithResponse() {
        Response responseToBeOverwritten = new Response();
        Stub stub = new Stub()
                .withResponse(responseToBeOverwritten);

        Response additionalResponse = new Response();
        stub.withResponse(additionalResponse);

        assertThat(stub.getResponses()).hasSize(1);
        assertThat(stub.getResponses()).doesNotContain(responseToBeOverwritten);
    }
}