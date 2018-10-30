package org.mbtest.javabank.http.predicates;

public class PredicateGenerator extends Predicate {

	private static final String PATH = "path";
	private static final String QUERY = "query";

	public PredicateGenerator(PredicateType type) {
		super(type);
	}

	private PredicateGenerator addEntry(String key, boolean value) {
		data.put(key, value);
		return this;
	}

	public PredicateGenerator withQueryParameters() {
		addEntry(QUERY, true);
		return this;
	}

	public PredicateGenerator withPath() {
		addEntry(PATH, true);
		return this;
	}

}
