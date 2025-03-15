package com.ziemsky;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Specification {

    @JsonProperty
    private final List<SpecificationPredicate> predicates = new ArrayList<>();

    @JsonProperty
    private final Object action = new Object();

    public static Specification of(final String specificationJson) {
        return fromJson(specificationJson);
    }

    public boolean isFulfilledBy(Arguments arguments) {
        return predicates.stream()
            .allMatch(predicate -> predicate.isSatisfiedBy(arguments));
    }

    private static Specification fromJson(final String specificationJson) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(specificationJson, Specification.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
