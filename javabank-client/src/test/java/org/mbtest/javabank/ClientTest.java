package org.mbtest.javabank;

import org.mbtest.javabank.fluent.ImposterBuilder;
import org.mbtest.javabank.http.imposters.Imposter;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@Ignore
public class ClientTest {

    private static final Client client = new Client();

    @Before
    public void setUp() {
        assertThatMountebankIsRunning();
        client.deleteAllImposters();
    }

    private void assertThatMountebankIsRunning() {
        if(!client.isMountebankRunning()) {
            fail("Mountebank is not running!");
        }
    }

    @Test
    public void shouldUseDefaultBaseUrlForDefaultConstructor() {
        assertThat(client.getBaseUrl()).isEqualTo(Client.DEFAULT_BASE_URL);
    }

    @Test
    public void shouldVerifyMountebankIsRunning() {
        assertThat(client.isMountebankRunning()).isEqualTo(true);
    }

    @Test
    public void shouldCreateAnImposter() {
        int statusCode = client.createImposter(new ImposterBuilder().onPort(5656).build());
        assertThat(statusCode).isEqualTo(201);
    }

    @Test
    public void shouldDeleteAnImposter() {
        Imposter imposter = new ImposterBuilder().onPort(5757).build();
        client.createImposter(imposter);

        String response = client.deleteImposter(5757);

        assertThat(response)
                .contains("5757")
                .contains("http");
    }

    @Test
    public void shouldCountTheNumberOfImposters() {
        client.createImposter(new ImposterBuilder().onPort(5858).build());
        client.createImposter(new ImposterBuilder().onPort(5959).build());
        client.createImposter(new ImposterBuilder().onPort(6060).build());

        assertThat(client.getImposterCount()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteAllImposters() {
        client.createImposter(new ImposterBuilder().onPort(6060).build());
        client.createImposter(new ImposterBuilder().onPort(6161).build());

        assertThat(client.deleteAllImposters()).isEqualTo(200);
        assertThat(client.getImposterCount()).isEqualTo(0);
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
        client.createImposter(expectedImposter);

        Imposter actualImposter = client.getImposter(6262);

        assertThat(actualImposter.getPort()).isEqualTo(expectedImposter.getPort());
//        assertThat(actualImposter.getStubs()).isEqualTo(expectedImposter.getStubs());
    }
}