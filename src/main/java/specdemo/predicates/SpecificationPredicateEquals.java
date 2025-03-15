package specdemo.predicates;

import specdemo.Arguments;

public class SpecificationPredicateEquals extends SpecificationPredicateBase {

    @Override public boolean isSatisfiedBy(final Arguments argument) {
        return argument.with(param).value().equals(value);
    }
}
