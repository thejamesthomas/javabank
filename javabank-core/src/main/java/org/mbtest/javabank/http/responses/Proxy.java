package org.mbtest.javabank.http.responses;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import org.mbtest.javabank.http.predicates.PredicateGenerator;

public class Proxy extends Response {

	private static final String PROXY = "proxy";
	private static final String MODE = "mode";
	private static final String TO = "to";
	private static final String PREDICATE_GENERATORS = "predicateGenerators";

	private final Map<String, Object> data;

	public Proxy(ProxyType proxyType) {
		this.data = newHashMap();
		this.data.put(MODE, proxyType.getValue());
		this.data.put(PREDICATE_GENERATORS, newArrayList());
		this.put(PROXY, data);
	}

	public Proxy proxyTo(String url) {
		this.data.put(TO, url);
		return this;
	}

	public List<PredicateGenerator> getPredicateGenerators() {
		return (List<PredicateGenerator>) this.data.get(PREDICATE_GENERATORS);
	}

	public Proxy addPredicateGenerators(List<PredicateGenerator> predicateGenerators) {
		getPredicateGenerators().addAll(predicateGenerators);
		return this;
	}

}
