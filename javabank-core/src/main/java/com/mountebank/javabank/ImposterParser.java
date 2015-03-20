package com.mountebank.javabank;

import com.mountebank.javabank.http.imposters.Imposter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImposterParser {

    public static Imposter parse(String json) throws ParseException {
        JSONObject parsedJson = (JSONObject) new JSONParser().parse(json);

        return Imposter.fromJSON(parsedJson);
    }
}
