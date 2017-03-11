package org.mbtest.javabank;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.mbtest.javabank.http.imposters.Imposter;

public class Client {

    static final String DEFAULT_BASE_URL = "http://localhost:2525";

    protected String baseUrl;

    public Client() {
        this(DEFAULT_BASE_URL);
    }

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Client(String host, int port) {
        this.baseUrl = String.format("http://%s:%i", host, port);
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public boolean isMountebankRunning() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(baseUrl).asJson();
            return response.getStatus() == 200;
        } catch (UnirestException e) {
            return false;
        }
    }

    public boolean isMountebankAllowingInjection() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(baseUrl + "/config").asJson();
            return response.getBody().getObject().getJSONObject("options").getBoolean("allowInjection");
        } catch (UnirestException e) {
            return false;
        }
    }

    public int createImposter(Imposter imposter) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(baseUrl + "/imposters").body(imposter.toString()).asJson();
            return response.getStatus();
        }
        catch (UnirestException e) {
            return 500;
        }
    }

    public String deleteImposter(int port) {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(baseUrl + "/imposters/" + port).asJson();
            return response.getBody().toString();
        }
        catch (UnirestException e) {
            return null;
        }
    }

    public int getImposterCount() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(baseUrl + "/imposters").asJson();
            return ((JSONArray)response.getBody().getObject().get("imposters")).length();
        }
        catch (UnirestException e) {
            return -1;
        }
    }

    public int deleteAllImposters() {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(baseUrl + "/imposters").asJson();
            return response.getStatus();
        }
        catch (UnirestException e) {
            return 500;
        }
    }

    public Imposter getImposter(int port) throws ParseException {
        try {
            HttpResponse<JsonNode> response = Unirest.get(baseUrl + "/imposters/" + port).asJson();
            String responseJson = response.getBody().toString();

            return ImposterParser.parse(responseJson);
        }
        catch (UnirestException e) {
            return null;
        }
    }
}
