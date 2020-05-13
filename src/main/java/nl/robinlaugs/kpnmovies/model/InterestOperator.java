package nl.robinlaugs.kpnmovies.model;

import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public enum InterestOperator {

    NONE(""),
    GREATER_THEN(">"),
    GREATER_THEN_OR_EQUAL_TO(">=", "+"),
    LESS_THEN("<"),
    LESS_THEN_OR_EQUAL_TO("<=", "-");

    private final Set<String> symbols;

    InterestOperator(String... operators) {
        this.symbols = stream(operators).collect(toSet());
    }

    public static InterestOperator findByValue(String value) {
        return stream(values())
                .filter(operator -> operator.symbols.contains(value))
                .findFirst().orElse(NONE);
    }

}
