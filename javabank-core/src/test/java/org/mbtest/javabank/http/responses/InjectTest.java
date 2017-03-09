package org.mbtest.javabank.http.responses;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InjectTest {

    public static final String FUNCTION = "some javascript function to be eval'd";

    @Test
    public void shouldCreateAJsonObject() throws Exception {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("inject", FUNCTION);

        Inject inject = new Inject()
                .withFunction(FUNCTION);

        assertThat(inject.getJSON(), is(expectedJson));
    }

    @Test
    public void shouldCreateAJsonString() throws Exception {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("inject", FUNCTION);

        Inject inject = new Inject()
                .withFunction(FUNCTION);

        assertThat(inject.toString(), is(expectedJson.toJSONString()));
    }
}