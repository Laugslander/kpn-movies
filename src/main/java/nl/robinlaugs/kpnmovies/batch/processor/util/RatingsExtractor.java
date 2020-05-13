package nl.robinlaugs.kpnmovies.batch.processor.util;

import nl.robinlaugs.kpnmovies.model.InterestOperator;

public class RatingsExtractor {

    public static double extract(String value, InterestOperator operator) {
        return switch (operator) {
            case NONE -> parseRating(value);
            case GREATER_THEN_OR_EQUAL_TO, LESS_THEN_OR_EQUAL_TO -> extractAndParseRating(value);
            default -> throw new IllegalArgumentException();
        };
    }

    private static double parseRating(String value) {
        return Double.parseDouble(value);
    }

    private static double extractAndParseRating(String value) {
        String rating = value.substring(0, value.length() - 1);

        return Double.parseDouble(rating);
    }

}
