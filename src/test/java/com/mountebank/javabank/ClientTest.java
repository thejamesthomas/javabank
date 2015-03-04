package com.mountebank.javabank;

import com.mountebank.javabank.fluent.ImposterBuilder;
import com.mountebank.javabank.http.imposters.Imposter;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

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
        assertThat(Client.createImposter(new ImposterBuilder().onPort(5656).build())).isEqualTo(201);
    }

    @Test
    public void shouldDeleteAnImposter() {
        Imposter imposter = new ImposterBuilder().onPort(5757).build();
        Client.createImposter(imposter);

        String response = Client.deleteImposter(5757);

        assertThat(response).contains("5757");
        assertThat(response).contains("http");
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
}