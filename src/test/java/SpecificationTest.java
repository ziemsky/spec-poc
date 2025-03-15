import static com.ziemsky.Arguments.Argument.argument;
import static com.ziemsky.Arguments.arguments;
import static com.ziemsky.Parameter.COUNTRY;
import static com.ziemsky.Parameter.MODEL;
import static org.assertj.core.api.Assertions.assertThat;

import com.ziemsky.Arguments;
import com.ziemsky.Specification;
import org.junit.jupiter.api.Test;

public class SpecificationTest {

    @Test
    void specIsFulfilledByMatchingArguments() {

        // given
        String specificationJson = """
            {
                "predicates": [
                    { "param": "COUNTRY" ,"match": "EQUALS" ,"value": "IND" },
                    { "param": "MODEL"   ,"match": "REGEX"  ,"value": "modelA|modelB" }
                ],
                "action": {}
            }
            """;

        Specification specification = Specification.of(specificationJson);

        Arguments arguments = arguments(
            argument(COUNTRY, "IND"),
            argument(MODEL, "modelB")
        );

        // when
        boolean specIsFulfilled = specification.isFulfilledBy(arguments);

        // then
        assertThat(specIsFulfilled).isTrue();
    }

    @Test
    void specIsNotFulfilledByNotMatchingArguments() {

        // given
        String specificationJson = """
            {
                "predicates": [
                    { "param": "COUNTRY" ,"match": "EQUALS" ,"value": "IND" },
                    { "param": "MODEL"   ,"match": "REGEX"  ,"value": "modelA|modelB" }
                ],
                "action": {}
            }
            """;

        Specification specification = Specification.of(specificationJson);

        final Arguments arguments = arguments(
            argument(COUNTRY, "GBR"),
            argument(MODEL, "modelB")
        );

        // when
        boolean specIsFulfilled = specification.isFulfilledBy(arguments);

        // then
        assertThat(specIsFulfilled).isFalse();
    }
}
