package org.mbtest.javabank.fluent;

import org.mbtest.javabank.http.core.Is;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

public class IsBuilder implements FluentBuilder {
    private ResponseBuilder parent;
    private int statusCode = 200;
    private String body = "";
    private File bodyFile;
    private final HashMap<String, String> headers = newHashMap();

    public IsBuilder(ResponseBuilder responseBuilder) {
        this.parent = responseBuilder;
    }

    public IsBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public IsBuilder header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public IsBuilder body(String body) {
        this.body = body;
        return this;
    }

    public IsBuilder body(File body) {
        this.bodyFile = body;
        return this;
    }

    public ResponseBuilder end() {
        return parent;
    }

    protected Is build() {

        if (this.bodyFile != null) {
            try {
                byte[] bytes = Files.readAllBytes(this.bodyFile.toPath());
                this.body = new String(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Is is = new Is().withStatusCode(statusCode).withHeaders(headers).withBody(body);
        return is;
    }
}
