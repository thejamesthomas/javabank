package com.mountebank.javabank.fluent;

import com.mountebank.javabank.Predicate;
import com.mountebank.javabank.fluent.matchers.EqualsBuilder;
import com.mountebank.javabank.matchers.MatcherConflictException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PredicateBuilder implements FluentBuilder {
    private StubBuilder parent;
    private List<EqualsBuilder> childEqualsBuilders = newArrayList();

    protected PredicateBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public EqualsBuilder equals() {
        EqualsBuilder equalsBuilder = new EqualsBuilder(this);
        childEqualsBuilders.add(equalsBuilder);
        return equalsBuilder;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected Predicate build() {
        Predicate predicate = new Predicate();
        for(EqualsBuilder equalsBuilder : childEqualsBuilders) {
            try {
                predicate.addMatcher(equalsBuilder.build());
            } catch (MatcherConflictException e) {
                e.printStackTrace();
            }
        }

        return predicate;
    }
}
