package specdemo;

import static java.util.stream.Collectors.toMap;

import lombok.Value;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class Arguments {

    private final Map<Parameter, Argument> arguments;

    private Arguments(final Argument... arguments) {
        this.arguments = Stream.of(arguments)
            .collect(toMap(
                argument -> argument.parameter,
                Function.identity()
            ));
    }

    public static Arguments arguments(Argument... argument) {
        return new Arguments(argument);
    }

    public Argument with(final Parameter parameter) {
        return arguments.get(parameter);
    }

    @Value(staticConstructor = "argument")
    public static class Argument {
        Parameter parameter;
        String value;
    }
}
