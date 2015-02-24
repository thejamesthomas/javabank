package com.mountebank.javabank.matchers.equals;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualsMatcherTest {
    @Test
    public void shouldSetMethodAndType() {
        String expectedType = "method";
        String expectedValue = "POST";

        EqualsMatcher equalsMatcher = new EqualsMatcher().withMethod(expectedValue);

        assertThat(equalsMatcher.getType()).isEqualTo(expectedType);
        assertThat(equalsMatcher.getValue()).isEqualTo(expectedValue);
    }

}