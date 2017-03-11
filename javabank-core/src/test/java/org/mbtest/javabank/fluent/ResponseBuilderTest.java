package org.mbtest.javabank.fluent;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ResponseBuilderTest {

    StubBuilder parent;
    ResponseBuilder responseBuilder;

    @Before
    public void setUp() throws Exception {
        parent = mock(StubBuilder.class);
        responseBuilder = new ResponseBuilder(parent);
    }

    @Test
    public void shouldConstructAnIsBuilderWithItselfAsTheParent() throws Exception {
        IsBuilder isBuilder = responseBuilder.is();

        assertThat(isBuilder.end(), is(responseBuilder));
    }

    @Test
    public void shouldEndWithItsParent() throws Exception {
        assertThat(responseBuilder.end(), is(parent));
    }

    @Test
    public void shouldBuildAnEmptyIsByDefault() throws Exception {
        HashMap expectedIs = new IsBuilder(responseBuilder).build();

        assertThat(responseBuilder.build(), is(expectedIs));
    }

    @Test
    public void shouldBuildAnIs() throws Exception {
        IsBuilder isBuilder = responseBuilder.is();
        isBuilder.body("some file");
        HashMap expectedIs = isBuilder.build();

        assertThat(responseBuilder.build(), is(expectedIs));
    }
}