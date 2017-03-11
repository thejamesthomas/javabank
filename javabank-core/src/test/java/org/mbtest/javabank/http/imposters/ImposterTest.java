package org.mbtest.javabank.http.imposters;

import org.apache.http.HttpHeaders;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mbtest.javabank.fluent.ImposterBuilder;
import org.mbtest.javabank.http.core.Stub;
import org.mbtest.javabank.http.responses.Is;

import static org.assertj.core.api.Assertions.assertThat;

public class ImposterTest {

    private String expectedProtocol = "http";
    private int expectedPort;

    @Before
    public void setUp() throws Exception {
        expectedPort = 4545;
    }

    @Test
    public void shouldCreateAnImposter() {
        Imposter imposter = Imposter.anImposter()
                .onPort(expectedPort);

        assertThat(imposter.getPort()).isEqualTo(expectedPort);
    }

    @Test
    public void shouldCreateAnHttpImposterWithAStub() {
        Stub expectedStub = new Stub();
        Imposter imposter = Imposter.anImposter()
                .withStub(expectedStub);

        assertThat(imposter.getStubs()).contains(expectedStub);
    }

    @Test
    public void shouldAddAStubToAnHttpImposter() {
        Stub additionalStub = new Stub();
        Imposter imposter = Imposter.anImposter()
                .withStub(new Stub());
        imposter.addStub(additionalStub);

        assertThat(imposter.getStubs()).hasSize(2);
        assertThat(imposter.getStubs()).contains(additionalStub);
    }

    @Test
    public void shouldCreateAJsonObject() {
        Imposter imposter = Imposter.anImposter()
                .onPort(expectedPort)
                .withStub(new Stub());

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("port", expectedPort);
        expectedJson.put("protocol", expectedProtocol);
        JSONArray stubs = new JSONArray();
        stubs.add(new Stub());
        expectedJson.put("stubs", stubs);

        assertThat(imposter.toJSON()).isEqualTo(expectedJson);
    }

    @Test
    public void shouldCreateAJsonObjectWithBuilder() {

        String expectedBody = "Hello World!";
        int expectedStatusCode = 201;
        String expectedContentType = "plain/text";

        Imposter imposter = ImposterBuilder.anImposter().onPort(expectedPort)
                .stub()
                  .response()
                    .is()
                      .body(expectedBody)
                      .statusCode(expectedStatusCode)
                      .header(com.google.common.net.HttpHeaders.CONTENT_TYPE, expectedContentType)
                    .end()
                  .end()
                .end().build();

        final Is response = new Is();
        response.withHeader(HttpHeaders.CONTENT_TYPE, expectedContentType);
        response.withBody(expectedBody);
        response.withStatusCode(expectedStatusCode);

        final Stub stub = new Stub();
        stub.addResponse(response);

        JSONArray stubs = new JSONArray();
        stubs.add(stub);

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("port", expectedPort);
        expectedJson.put("protocol", expectedProtocol);
        expectedJson.put("stubs", stubs);

        assertThat(imposter.toJSON()).isEqualTo(expectedJson);
    }
}