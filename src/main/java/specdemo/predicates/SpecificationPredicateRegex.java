package specdemo.predicates;

import specdemo.Arguments;

public class SpecificationPredicateRegex extends SpecificationPredicateBase {

    @Override public boolean isSatisfiedBy(final Arguments argument) {
        return argument.with(param).value().matches(value);
    }
}
