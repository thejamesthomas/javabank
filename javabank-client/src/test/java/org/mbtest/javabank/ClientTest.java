package org.mbtest.javabank;

import com.mountebank.javabank.fluent.ImposterBuilder;
import com.mountebank.javabank.http.imposters.Imposter;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@Ignore
public class ClientTest {
    @Before
    public void setUp() {
        assertThatMountebankIsRunning();
        Client.deleteAllImposters();
    }

    private void assertThatMountebankIsRunning() {
        if(!Client.isMountebankRunning()) {
            fail("Mountebank is not running!");
        }
    }

    @Test
    public void shouldVerifyMountebankIsRunning() {
        assertThat(Client.isMountebankRunning()).isEqualTo(true);
    }

    @Test
    public void shouldCreateAnImposter() {
        int statusCode = Client.createImposter(new ImposterBuilder().onPort(5656).build());
        assertThat(statusCode).isEqualTo(201);
    }

    @Test
    public void shouldDeleteAnImposter() {
        Imposter imposter = new ImposterBuilder().onPort(5757).build();
        Client.createImposter(imposter);

        String response = Client.deleteImposter(5757);

        assertThat(response)
                .contains("5757")
                .contains("http");
    }

    @Test
    public void shouldCountTheNumberOfImposters() {
        Client.createImposter(new ImposterBuilder().onPort(5858).build());
        Client.createImposter(new ImposterBuilder().onPort(5959).build());
        Client.createImposter(new ImposterBuilder().onPort(6060).build());

        assertThat(Client.getImposterCount()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteAllImposters() {
        Client.createImposter(new ImposterBuilder().onPort(6060).build());
        Client.createImposter(new ImposterBuilder().onPort(6161).build());

        assertThat(Client.deleteAllImposters()).isEqualTo(200);
        assertThat(Client.getImposterCount()).isEqualTo(0);
    }

    @Test
    public void shouldGetAnImposter() throws ParseException {
        Imposter expectedImposter = new ImposterBuilder()
            .onPort(6262)
            .stub()
                .predicate()
                    .startsWith()
                        .body("testing")
                    .end()
                .end()
                .response()
                    .is()
                        .body("hello, world")
                    .end()
                .end()
            .end()
        .build();
        Client.createImposter(expectedImposter);

        Imposter actualImposter = Client.getImposter(6262);

        assertThat(actualImposter.getPort()).isEqualTo(expectedImposter.getPort());
//        assertThat(actualImposter.getStubs()).isEqualTo(expectedImposter.getStubs());
    }
}