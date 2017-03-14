package org.mbtest.javabank.fluent;

import org.junit.Before;
import org.junit.Test;
import org.mbtest.javabank.http.responses.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ResponseTypeBuilderTest {

    private ResponseBuilder parent;
    private ResponseTypeBuilder responseTypeBuilder;

    @Before
    public void setUp() throws Exception {
        parent = mock(ResponseBuilder.class);
        responseTypeBuilder = new TestResponseBuilder(parent);

    }

    @Test
    public void shouldEndWithItsParent() throws Exception {
        assertThat(responseTypeBuilder.end(), is(parent));
    }

    private class TestResponseBuilder extends ResponseTypeBuilder {

        TestResponseBuilder(ResponseBuilder parent) {
            super(parent);
        }

        @Override
        protected Response build() {
            return null;
        }
    }
}