package com.mountebank.javabank;

import com.mountebank.javabank.Imposter;
import com.mountebank.javabank.Stub;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ImposterTest {

    private String expectedProtocol;
    private int expectedPort;

    @Before
    public void setUp() throws Exception {
        expectedProtocol = "http";
        expectedPort = 4545;
    }

    @Test
    public void shouldCreateAnImposter() {
        Imposter imposter = Imposter.anImposter()
                .withPort(expectedPort)
                .withProtocol(expectedProtocol);

        assertThat(imposter.getPort()).isEqualTo(expectedPort);
        assertThat(imposter.getProtocol()).isEqualTo(expectedProtocol);
    }

    @Test
    public void shouldCreateAnHttpImposterWithAStub() {
        Stub expectedStub = new Stub();
        Imposter imposter = Imposter.anHttpImposter()
                .withStub(expectedStub);

        assertThat(imposter.getStubs()).contains(expectedStub);
    }

    @Test
    public void shouldAddAStubToAnHttpImposter() {
        Stub additionalStub = new Stub();
        Imposter imposter = Imposter.anHttpImposter()
                .withStub(new Stub());
        imposter.addStub(additionalStub);

        assertThat(imposter.getStubs()).hasSize(2);
        assertThat(imposter.getStubs()).contains(additionalStub);
    }

    @Test
    public void shouldCreateAJsonObject() {
        Imposter imposter = Imposter.anImposter()
                .withProtocol(expectedProtocol)
                .withPort(expectedPort)
                .withStub(new Stub());

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("port", expectedPort);
        expectedJson.put("protocol", expectedProtocol);
        expectedJson.put("stubs", new JSONArray().add(new Stub()));

        assertThat(imposter.toJSON()).isEqualTo(expectedJson);
    }

    @Test
    public void shouldNotCreateAStubsElementInJsonWhenStubsAreEmpty() {
        Imposter imposter = Imposter.anImposter()
                .withProtocol(expectedProtocol)
                .withPort(expectedPort);

        assertThat(imposter.toString()).doesNotContain("stubs");
    }
}