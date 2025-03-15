# Specification pattern implementation demo 

Just a basic illustration of a possible implementation of an action executed conditionally based on a specification configured in a database.

The core logic is implemented in class [Specification](src/main/java/specdemo/Specification.java).

It contains:
* a set of predicates to be evaluated against [Arguments](src/main/java/specdemo/Arguments.java),
* a configuration to be applied to the incoming collection of [Field](src/main/java/specdemo/Field.java)s.

All predicates have to evaluate to 'true' for the specification to be considered as fulfilled by the `Arguments`.

The `Specification` is meant to be deserialised from JSON payload returned by a REST endpoint. The endpoint retrieves (or builds) the JSON where the specification is configured.

[SpecificationTest.appliesConfigOnMatchingArguments](src/test/java/SpecificationTest.java) shows the full example usage of a single specification. The other two test methods focus on evaluation of predicates. 

The current implementation only supports the 'basic' variant of the specification.

See [doc/notes](doc/notes.md) for more details (inc. JSON payload and possible DB schema) and for a brief mention of a variant supporting more elaborate predicates.