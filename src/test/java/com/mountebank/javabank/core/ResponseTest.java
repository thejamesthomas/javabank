package com.mountebank.javabank.core;

import com.mountebank.javabank.core.Is;
import com.mountebank.javabank.core.Response;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ResponseTest {
    @Test
    public void shouldAddAnIsToTheIsList() {
        Response response = new Response().withIs(new Is());

        assertThat(response.getIsList()).hasSize(1);
    }

    @Test
    public void shouldOverwriteIsListWhenASecondWithIsIsUsed() {
        Is isToBeOverwritten = new Is();
        Response response = new Response().withIs(isToBeOverwritten);

        response.withIs(new Is());

        assertThat(response.getIsList()).doesNotContain(isToBeOverwritten);
    }

    @Test
    public void shouldAddAdditionalIsToTheIsList() {
        Response response = new Response().withIs(new Is());

        Is additionalIs = new Is();
        response.addIs(additionalIs);

        assertThat(response.getIsList()).contains(additionalIs);
    }
}