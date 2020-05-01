package nl.robinlaugs.kpnmovies.service.movie;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public enum TimeIndicator {

    HOURS(TimeIndicator::convertHoursToMinutes),
    MINUTES(TimeIndicator::convertMinutesToMinutes);

    public final Function<String, Duration> durationConverter;

    public static TimeIndicator findByValue(String value) {
        return Arrays.stream(TimeIndicator.values())
                .filter(runtimeOperator -> runtimeOperator.toString().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private static Duration convertMinutesToMinutes(String value) {
        return Duration.ofMinutes(Long.parseLong(value));
    }

    private static Duration convertHoursToMinutes(String value) {
        return Duration.ofHours(Long.parseLong(value));
    }

}
