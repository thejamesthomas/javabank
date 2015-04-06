package org.mbtest.javabank;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mbtest.javabank.http.imposters.Imposter;

public class ImposterParser {

    public static Imposter parse(String json) throws ParseException {
        JSONObject parsedJson = (JSONObject) new JSONParser().parse(json);

        return Imposter.fromJSON(parsedJson);
    }
}
