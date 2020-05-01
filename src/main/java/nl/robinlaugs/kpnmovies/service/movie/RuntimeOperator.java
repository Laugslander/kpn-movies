package nl.robinlaugs.kpnmovies.service.movie;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.data.MovieRepository;
import nl.robinlaugs.kpnmovies.domain.Movie;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public enum RuntimeOperator {

    NONE("", RuntimeOperator::parseRuntimeTimeIndicatorAbsent, MovieRepository::findByRuntime),
    GREATER_THEN(">", RuntimeOperator::parseRuntimeTimeIndicatorPresent, MovieRepository::findByRuntimeGreaterThan),
    LESS_THEN("<", RuntimeOperator::parseRuntimeTimeIndicatorPresent, MovieRepository::findByRuntimeLessThan);

    private final String operator;

    public final Function<String[], Duration> runtimeExtractor;
    public final BiFunction<MovieRepository, Duration, List<Movie>> movieFinder;

    public static RuntimeOperator findByValue(String value) {
        return Arrays.stream(RuntimeOperator.values())
                .filter(ratingOperator -> ratingOperator.operator.equalsIgnoreCase(value))
                .findFirst().orElse(NONE);
    }

    private static Duration parseRuntimeTimeIndicatorPresent(String[] attributes) {
        TimeIndicator timeIndicator = TimeIndicator.findByValue(attributes[2]);

        return timeIndicator.durationConverter.apply(attributes[1]);
    }

    private static Duration parseRuntimeTimeIndicatorAbsent(String[] attributes) {
        TimeIndicator timeIndicator = TimeIndicator.findByValue(attributes[1]);

        return timeIndicator.durationConverter.apply(attributes[0]);
    }

}
