package org.mbtest.javabank.http.predicates;

import static com.google.common.collect.Maps.newHashMap;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Predicate extends HashMap {
	private static final String PATH = "path";
	private static final String METHOD = "method";
	private static final String QUERY = "query";
	private static final String HEADERS = "headers";
	private static final String REQUEST_FROM = "requestFrom";
	private static final String BODY = "body";

	protected Map data;
	private PredicateType type;

	public Predicate(PredicateType type) {
		this.type = type;
		data = newHashMap();
		this.put(type.getValue(), data);
	}

	private Predicate addEntry(String key, String value) {
		data.put(key, value);
		return this;
	}

	private Predicate addEntry(String key, Map map) {
		data.put(key, map);
		return this;
	}

	private Predicate addMapEntry(String key, String name, String value) {
		if (!data.containsKey(key)) {
			data.put(key, newHashMap());
		}

		Map entryMap = (Map) data.get(key);
		entryMap.put(name, value);

		return this;
	}

	private Object getEntry(String key) {
		return this.data.get(key);
	}

	@Override
	public String toString() {
		return toJSON().toJSONString();
	}

	private JSONObject toJSON() {
		return new JSONObject(this);
	}

	public String getType() {
		return type.getValue();
	}

	public Predicate withPath(String path) {
		addEntry(PATH, path);
		return this;
	}

	public Predicate withMethod(String method) {
		addEntry(METHOD, method);
		return this;
	}

	public Predicate withQueryParameters(Map<String, String> parameters) {
		addEntry(QUERY, parameters);
		return this;
	}

	public Predicate addQueryParameter(String name, String value) {
		addMapEntry(QUERY, name, value);
		return this;
	}

	public Predicate addHeader(String name, String value) {
		addMapEntry(HEADERS, name, value);
		return this;
	}

	public Predicate withRequestFrom(String host, String port) {
		addEntry(REQUEST_FROM, host + ":" + port);
		return this;
	}

	public Predicate withBody(String body) {
		addEntry(BODY, body);
		return this;
	}

	public String getPath() {
		return (String) getEntry(PATH);
	}

	public String getMethod() {
		return (String) getEntry(METHOD);
	}

	public String getRequestFrom() {
		return (String) getEntry(REQUEST_FROM);
	}

	public String getBody() {
		return (String) getEntry(BODY);
	}

	public Map<String, String> getQueryParameters() {
		return (Map<String, String>) getEntry(QUERY);
	}

	public String getQueryParameter(String parameter) {
		return getQueryParameters().get(parameter);
	}

	public Map<String, String> getHeaders() {
		return (Map<String, String>) getEntry(HEADERS);
	}

	public String getHeader(String name) {
		return getHeaders().get(name);
	}
}
