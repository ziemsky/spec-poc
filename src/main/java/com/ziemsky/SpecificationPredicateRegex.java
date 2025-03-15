package com.ziemsky;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecificationPredicateRegex implements SpecificationPredicate {

    @JsonProperty
    private Parameter param;

    @JsonProperty
    private String value;

    @Override public boolean isSatisfiedBy(final Arguments argument) {
        return argument.with(param).value().matches(value);
    }
}
