package org.mbtest.javabank.http.core;

import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mbtest.javabank.http.responses.Is;

import static org.assertj.core.api.Assertions.assertThat;

public class IsTest {

    private ImmutableMap<String, String> defaultHeaders;

    @Before
    public void setUp() throws Exception {
        defaultHeaders = ImmutableMap.of(HttpHeaders.CONNECTION, "close");
    }

    @Test
    public void shouldSetTheDefaultHttpStatusCodeTo200() {
        Is is = new Is();

        assertThat(is.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void shouldSetTheDefaultHeadersToConnectionClose() {
        Is is = new Is();

        assertThat(is.getHeaders()).isEqualTo(defaultHeaders);
    }

    @Test
    public void shouldSetTheDefaultBodyToEmpty() {
        Is is = new Is();

        assertThat(is.getBody()).isEqualTo("");
    }

    @Test
    public void shouldSetTheStatusCode() {
        Is is = new Is()
            .withStatusCode(HttpStatus.SC_BAD_REQUEST);

        assertThat(is.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldSetTheHeaders() {
        Is is = new Is().withHeader(HttpHeaders.CONTENT_TYPE, "application/xml");

        ImmutableMap<String, String> expectedHeaders = ImmutableMap.of(HttpHeaders.CONTENT_TYPE, "application/xml");

        assertThat(is.getHeaders()).isEqualTo(expectedHeaders);
    }

    @Test
    public void shouldSetTheBody() {
        String expectedBody = "Hello World!";
        Is is = new Is().withBody(expectedBody);

        assertThat(is.getBody()).isEqualTo(expectedBody);
    }

    @Test
    public void shouldSetTheMode(){
        Is is = new Is().withMode("binary");
        assertThat(is.getMode()).isEqualTo("binary");
    }

    @Test
    public void shouldNotSetAModeByDefault() throws Exception {
        Is is = new Is();
        assertThat(is.getMode()).isNull();
        assertThat(is.getJSON().containsKey(Is.MODE)).isFalse();
    }

    @Test
    public void shouldContainAllDefaultValuesInJson() {
        Is is = new Is();

        JSONObject innerJson = new JSONObject();
        innerJson.put("statusCode", 200);
        innerJson.put("headers", defaultHeaders);
        innerJson.put("body", "");

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("is", innerJson);

        assertThat(is.getJSON()).isEqualTo(expectedJson);
    }

    @Test
    public void shouldAddAdditionalHeaders() {
        Is is = new Is()
            .withHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
            .addHeader(HttpHeaders.ACCEPT, "*");

        ImmutableMap<String, String> expectedHeaders = ImmutableMap.of(HttpHeaders.CONTENT_TYPE, "text/plain", HttpHeaders.ACCEPT, "*");
        assertThat(is.getHeaders()).isEqualTo(expectedHeaders);
    }
}
