package org.mbtest.javabank.fluent;

import org.junit.Before;
import org.junit.Test;
import org.mbtest.javabank.http.responses.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class InjectBuilderTest {

    private InjectBuilder injectBuilder;

    @Before
    public void setUp() throws Exception {
        injectBuilder = new InjectBuilder(mock(ResponseBuilder.class));
    }

    @Test
    public void shouldBuildAnInjectWithAFunction() throws Exception {
        String injection = "some function";
        Inject expectedInject = new Inject().withFunction(injection);
        Inject actualInject = injectBuilder.function(injection).build();

        assertThat(actualInject, is(expectedInject));
    }
}