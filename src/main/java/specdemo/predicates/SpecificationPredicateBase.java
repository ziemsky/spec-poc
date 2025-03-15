package specdemo.predicates;

import specdemo.Parameter;
import specdemo.SpecificationPredicate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SpecificationPredicateBase implements SpecificationPredicate {

    @JsonProperty
    protected Parameter param;

    @JsonProperty
    protected String value;
}
