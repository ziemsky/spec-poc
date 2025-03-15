import static specdemo.Arguments.Argument.argument;
import static specdemo.Arguments.arguments;
import static specdemo.Field.field;
import static specdemo.Parameter.COUNTRY;
import static specdemo.Parameter.MODEL;
import static org.assertj.core.api.Assertions.assertThat;

import specdemo.Arguments;
import specdemo.Field;
import specdemo.Specification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SpecificationTest {

    Specification specification = Specification.of("""
        {
            "predicates": [
                { "param": "COUNTRY" ,"match": "EQUALS" ,"value": "IND" },
                { "param": "MODEL"   ,"match": "REGEX"  ,"value": "modelA|modelB" }
            ],
            "config": [
                { "name": "fieldA", "value": "valueA" },
                { "name": "fieldB", "value": "valueB" }
            ]
        }
        """);

    @Test
    void specIsFulfilledByMatchingArguments() {

        // given
        Arguments argumentsMatchingSpecPredicates = arguments(
            argument(COUNTRY, "IND"),
            argument(MODEL, "modelB")
        );

        // when
        boolean specIsFulfilled = specification.isFulfilledBy(argumentsMatchingSpecPredicates);

        // then
        assertThat(specIsFulfilled).isTrue();
    }

    @Test
    void specIsNotFulfilledByNotMatchingArguments() {

        // given
        final Arguments argumentsNotMatchingSpecPredicates = arguments(
            argument(COUNTRY, "GBR"),
            argument(MODEL, "modelB")
        );

        // when
        boolean specIsFulfilled = specification.isFulfilledBy(argumentsNotMatchingSpecPredicates);

        // then
        assertThat(specIsFulfilled).isFalse();
    }

    @Test
    void appliesConfigOnMatchingArguments() {

        // given
        Arguments argumentsMatchingSpecPredicates = arguments(
            argument(COUNTRY, "IND"),
            argument(MODEL, "modelB")
        );

        List<Field> fieldsToUpdate = new ArrayList<>(List.of(
            field("fieldA", "valueAOriginal")
        ));

        List<Field> expectedFieldsUpdated = new ArrayList<>(List.of(
            field("fieldA", "valueA"),
            field("fieldB", "valueB")
        ));

        // when
        if (specification.isFulfilledBy(argumentsMatchingSpecPredicates)) {
            specification.applyConfigTo(fieldsToUpdate);
        }

        // then
        assertThat(fieldsToUpdate).isEqualTo(expectedFieldsUpdated);
    }
}
