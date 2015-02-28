package com.mountebank.javabank.imposters.http;

import com.mountebank.javabank.core.Stub;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpImposterTest {

    private String expectedProtocol = "http";
    private int expectedPort;

    @Before
    public void setUp() throws Exception {
        expectedPort = 4545;
    }

    @Test
    public void shouldCreateAnImposter() {
        HttpImposter httpImposter = HttpImposter.anImposter()
                .onPort(expectedPort);

        assertThat(httpImposter.getPort()).isEqualTo(expectedPort);
    }

    @Test
    public void shouldCreateAnHttpImposterWithAStub() {
        Stub expectedStub = new Stub();
        HttpImposter httpImposter = HttpImposter.anImposter()
                .withStub(expectedStub);

        assertThat(httpImposter.getStubs()).contains(expectedStub);
    }

    @Test
    public void shouldAddAStubToAnHttpImposter() {
        Stub additionalStub = new Stub();
        HttpImposter httpImposter = HttpImposter.anImposter()
                .withStub(new Stub());
        httpImposter.addStub(additionalStub);

        assertThat(httpImposter.getStubs()).hasSize(2);
        assertThat(httpImposter.getStubs()).contains(additionalStub);
    }

    @Test
    public void shouldCreateAJsonObject() {
        HttpImposter httpImposter = HttpImposter.anImposter()
                .onPort(expectedPort)
                .withStub(new Stub());

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("port", expectedPort);
        expectedJson.put("protocol", expectedProtocol);
        expectedJson.put("stubs", new JSONArray().add(new Stub()));

        assertThat(httpImposter.toJSON()).isEqualTo(expectedJson);
    }

    @Test
    public void shouldNotCreateAStubsElementInJsonWhenStubsAreEmpty() {
        HttpImposter httpImposter = HttpImposter.anImposter()
                .onPort(expectedPort);

        assertThat(httpImposter.toString()).doesNotContain("stubs");
    }
}