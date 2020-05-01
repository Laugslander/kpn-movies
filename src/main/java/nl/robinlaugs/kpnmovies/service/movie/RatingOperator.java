package nl.robinlaugs.kpnmovies.service.movie;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.data.MovieRepository;
import nl.robinlaugs.kpnmovies.domain.Movie;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public enum RatingOperator {

    NONE("", RatingOperator::parseDouble, MovieRepository::findByRating),
    PLUS("+", RatingOperator::extractAndParseDouble, MovieRepository::findByRatingGreaterThanEqual),
    MIN("-", RatingOperator::extractAndParseDouble, MovieRepository::findByRatingLessThanEqual);

    private final String operator;

    public final Function<String, Double> ratingExtractor;
    public final BiFunction<MovieRepository, Double, List<Movie>> movieFinder;

    public static RatingOperator findByValue(String value) {
        return Arrays.stream(RatingOperator.values())
                .filter(ratingOperator -> ratingOperator.operator.equalsIgnoreCase(value))
                .findFirst().orElse(NONE);
    }

    private static double parseDouble(String value) {
        return Double.parseDouble(value);
    }

    private static double extractAndParseDouble(String value) {
        return Double.parseDouble(value.substring(0, value.length() - 1));
    }

}
