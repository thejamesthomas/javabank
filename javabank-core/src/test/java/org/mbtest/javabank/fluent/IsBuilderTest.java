package org.mbtest.javabank.fluent;

import org.junit.Test;
import org.mbtest.javabank.http.core.Is;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class IsBuilderTest {

    @Test
    public void testBuildWithFileBody() throws Exception {
        IsBuilder testSubject = new IsBuilder(null);
        final Is is = testSubject.body(new File("src/test/resources/response.json")).build();

        assertThat(is.getBody()).contains("name1", "value1", "name2", "value2");
    }
}