package com.ziemsky;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "match"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SpecificationPredicateEquals.class, name = "EQUALS"),
    @JsonSubTypes.Type(value = SpecificationPredicateRegex.class, name = "REGEX")
})
public interface SpecificationPredicate {
    boolean isSatisfiedBy(final Arguments arguments);
}
