package org.mbtest.javabank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbtest.javabank.fluent.ImposterBuilder;
import org.mbtest.javabank.http.imposters.Imposter;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Unirest.class, ImposterParser.class })
public class ClientTest {

    private static final Client client = new Client();

    @Mock
    private GetRequest getRequest;

    @Mock
    private HttpResponse<JsonNode> httpResponse;

    @Mock
    private HttpRequestWithBody requestWithBody;

    @Mock
    private RequestBodyEntity requestBodyEntity;

    @Mock
    JsonNode value;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Unirest.class);
        PowerMockito.mockStatic(ImposterParser.class);
    }

    @Test
    public void shouldUseDefaultBaseUrlForDefaultConstructor() {
        assertThat(client.getBaseUrl()).isEqualTo(Client.DEFAULT_BASE_URL);
    }

    @Test
    public void shouldVerifyMountebankIsRunning() throws UnirestException {
        when(Unirest.get(Client.DEFAULT_BASE_URL)).thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(Integer.valueOf(200));

        assertThat(client.isMountebankRunning()).isEqualTo(true);
    }

    @Test
    public void shouldCreateAnImposter() throws UnirestException {
        Imposter imposter = new Imposter();
        when(Unirest.post(Client.DEFAULT_BASE_URL + "/imposters")).thenReturn(requestWithBody);
        when(requestWithBody.body(imposter.toString())).thenReturn(requestBodyEntity);
        when(requestBodyEntity.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(Integer.valueOf(201));

        int statusCode = client.createImposter(imposter);

        assertThat(statusCode).isEqualTo(201);
    }

    @Test
    public void shouldDeleteAnImposter() throws UnirestException {
        String deleteResponse = "{ url: http://localhost:5757 }";
        when(Unirest.delete(Client.DEFAULT_BASE_URL + "/imposters/5757")).thenReturn(requestWithBody);
        when(requestWithBody.asJson()).thenReturn(httpResponse);
        when(httpResponse.getBody()).thenReturn(value);
        when(value.toString()).thenReturn(deleteResponse);

        String response = client.deleteImposter(5757);

        assertThat(response).contains("5757").contains("http");
    }

    @Test
    public void shouldCountTheNumberOfImposters() throws UnirestException {
        JsonNode jsonNode = mock(JsonNode.class);
        JSONObject jsonObject = mock(JSONObject.class);
        JSONArray jsonArray = mock(JSONArray.class);
        when(Unirest.get(Client.DEFAULT_BASE_URL + "/imposters")).thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(httpResponse);
        when(httpResponse.getBody()).thenReturn(jsonNode);
        when(jsonNode.getObject()).thenReturn(jsonObject);
        when(jsonObject.get("imposters")).thenReturn(jsonArray);
        when(jsonArray.length()).thenReturn(Integer.valueOf(3));

        assertThat(client.getImposterCount()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteAllImposters() throws UnirestException {
        when(Unirest.delete(Client.DEFAULT_BASE_URL + "/imposters")).thenReturn(requestWithBody);
        when(requestWithBody.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(Integer.valueOf(200));

        assertThat(client.deleteAllImposters()).isEqualTo(200);
    }

    @Test
    public void shouldGetAnImposter() throws ParseException, UnirestException {
        int port = 5757;
        String jsonString = "test string";
        Imposter expectedImposter = new ImposterBuilder().onPort(5757).build();
        when(Unirest.get(Client.DEFAULT_BASE_URL + "/imposters/5757")).thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(httpResponse);
        when(httpResponse.getBody()).thenReturn(value);
        when(value.toString()).thenReturn(jsonString);
        when(ImposterParser.parse(jsonString)).thenReturn(expectedImposter);

        Imposter actualImposter = client.getImposter(port);
        assertThat(actualImposter.getPort()).isEqualTo(expectedImposter.getPort());
    }

    @Test
    public void assertThatMountebankAllowsInjection() throws UnirestException {
        JSONObject jsonObjectOptions = mock(JSONObject.class);
        JSONObject jsonObjectAllowInjection = mock(JSONObject.class);
        when(Unirest.get(Client.DEFAULT_BASE_URL + "/config")).thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(httpResponse);
        when(httpResponse.getBody()).thenReturn(value);
        when(value.getObject()).thenReturn(jsonObjectOptions);
        when(jsonObjectOptions.getJSONObject("options")).thenReturn(jsonObjectAllowInjection);
        when(jsonObjectAllowInjection.getBoolean("allowInjection")).thenReturn(Boolean.TRUE);

        if (!client.isMountebankAllowingInjection()) {
            fail("Mountebank is not running with --allowInjection!");
        }
    }

    @Test
    public void assertTwoArgumentConstructorSetsBaseUrlCorrectly() {
        String host = "localhost";
        int port = 5757;

        Client client = new Client(host, port);
        assertEquals("http://localhost:5757", client.getBaseUrl());
    }

    @Test
    public void assertOneArgumentConstructorSetsBaseUrlCorrectly() {
        String host = "localhost";
        int port = 5757;

        Client client = new Client("http://" + host + ":" + port);
        assertEquals("http://localhost:5757", client.getBaseUrl());
    }
}