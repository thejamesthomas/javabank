package com.mountebank.javabank;

import com.mountebank.javabank.fluent.ImposterBuilder;
import com.mountebank.javabank.http.imposters.Imposter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImposterParserTest {
    @Test
    public void shouldParseJSONIntoImposter() throws ParseException {
        Imposter expectedImposter = ImposterBuilder.anImposter().onPort(1234).build();

        Imposter actualImposter = ImposterParser.parse(expectedImposter.toString());

        assertThat(actualImposter.getPort()).isEqualTo(expectedImposter.getPort());
    }

    @Ignore
    @Test
    public void shouldReturnAnImposterWithAnIntegerForThePort() throws ParseException {
        Imposter expectedImposter = ImposterBuilder.anImposter().onPort(1234).build();

        Imposter actualImposter = ImposterParser.parse(expectedImposter.toString());

        assertThat(actualImposter.get("port")).hasSameClassAs(new Integer(1));
    }

    @Ignore
    @Test
    public void shouldReturnAnImposterWithIntegersForTheResponseStatusCode() throws ParseException {
        Imposter expectedImposter = ImposterBuilder.anImposter()
            .onPort(1234)
            .stub()
                .response()
                    .is()
                        .statusCode(200)
                    .end()
                .end()
            .end()
        .build();

        Imposter actualImposter = ImposterParser.parse(expectedImposter.toString());

        JSONArray stubs = (JSONArray) actualImposter.get("stubs");
        JSONObject stub = (JSONObject) stubs.get(0);
        JSONArray responses = (JSONArray) stub.get("responses");
        JSONObject response = (JSONObject) responses.get(0);
        JSONObject is = (JSONObject) response.get("is");
        assertThat(is.get("statusCode")).hasSameClassAs(new Integer(1));
    }
}