package specdemo;

import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.function.Function;

public class Specification {

    @JsonProperty("predicates")
    private final List<SpecificationPredicate> predicates = new ArrayList<>();

    @JsonProperty("config")
    private final List<Field> config = new ArrayList<>();

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

    public void applyConfigTo(final List<Field> fieldsToUpdate) {
        Map<String, Field> incomingConfigFieldsByName = mapByName(config);

        final List<Field> fieldsUpdated = new LinkedList<>();
        fieldsToUpdate.forEach(fieldToUpdate ->
            fieldsUpdated.add(
                incomingConfigFieldsByName.getOrDefault(fieldToUpdate.name(), fieldToUpdate))
        );

        Map<String, Field> fieldsToUpdateByName = mapByName(fieldsToUpdate);
        config.forEach(incomingConfigField -> {
            if (!fieldsToUpdateByName.containsKey(incomingConfigField.name())) {
                fieldsUpdated.add(incomingConfigField);
            }
        });

        fieldsToUpdate.clear();
        fieldsToUpdate.addAll(fieldsUpdated);
    }

    private Map<String, Field> mapByName(final List<Field> fields) {
        return fields.stream()
            .collect(toMap(Field::name, Function.identity()));
    }
}
