package com.mountebank.javabank;

import com.mountebank.javabank.fluent.ImposterBuilder;
import com.mountebank.javabank.http.imposters.Imposter;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImposterParserTest {
    @Test
    public void shouldParseJSONIntoImposter() throws ParseException {
        Imposter expectedImposter = ImposterBuilder.anImposter().onPort(1234).build();

        Imposter actualImposter = ImposterParser.parse(expectedImposter.toString());

        assertThat(actualImposter.getPort()).isEqualTo(expectedImposter.getPort());
    }
}