package org.mbtest.javabank.http.predicates;

public class PredicateGenerator extends Predicate {

	public PredicateGenerator(PredicateType type) {
		super(type);
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
