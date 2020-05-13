package nl.robinlaugs.kpnmovies.batch.processor.util;

import nl.robinlaugs.kpnmovies.model.InterestOperator;

import java.time.Duration;

public class RuntimeExtractor {

    public static Duration extract(String[] attributes, InterestOperator operator) {
        return switch (operator) {
            case NONE -> parseAndConvertOperatorAbsent(attributes);
            case GREATER_THEN, LESS_THEN -> parseAndConvertOperatorPresent(attributes);
            default -> throw new IllegalArgumentException();
        };
    }

    private static Duration parseAndConvertOperatorPresent(String[] attributes) {
        TimeConverter timeConverter = TimeConverter.findByValue(attributes[2]);

        return timeConverter.durationConverter.apply(attributes[1]);
    }

    private static Duration parseAndConvertOperatorAbsent(String[] attributes) {
        TimeConverter timeConverter = TimeConverter.findByValue(attributes[1]);

        return timeConverter.durationConverter.apply(attributes[0]);
    }

}
