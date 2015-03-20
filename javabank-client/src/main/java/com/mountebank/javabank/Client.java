package com.mountebank.javabank;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mountebank.javabank.http.imposters.Imposter;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;

public class Client {

    public static final String BASE_URL = "http://localhost:2525";

    public static boolean isMountebankRunning() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(BASE_URL).asJson();
            return response.getStatus() == 200;
        } catch (UnirestException e) {
            return false;
        }
    }

    public static int createImposter(Imposter imposter) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(BASE_URL + "/imposters").body(imposter.toString()).asJson();
            return response.getStatus();
        }
        catch (UnirestException e) {
            return 500;
        }
    }

    public static String deleteImposter(int port) {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(BASE_URL + "/imposters/" + port).asJson();
            return response.getBody().toString();
        }
        catch (UnirestException e) {
            return null;
        }
    }

    public static int getImposterCount() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(BASE_URL + "/imposters").asJson();
            return ((JSONArray)response.getBody().getObject().get("imposters")).length();
        }
        catch (UnirestException e) {
            return -1;
        }
    }

    public static int deleteAllImposters() {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(BASE_URL + "/imposters").asJson();
            return response.getStatus();
        }
        catch (UnirestException e) {
            return 500;
        }
    }

    public static Imposter getImposter(int port) throws ParseException {
        try {
            HttpResponse<JsonNode> response = Unirest.get(BASE_URL + "/imposters/" + port).asJson();
            String responseJson = response.getBody().toString();

            return ImposterParser.parse(responseJson);
        }
        catch (UnirestException e) {
            return null;
        }
    }
}
