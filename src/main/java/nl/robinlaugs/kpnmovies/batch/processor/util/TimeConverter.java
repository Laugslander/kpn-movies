package nl.robinlaugs.kpnmovies.batch.processor.util;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.function.Function;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
public enum TimeConverter {

    HOURS(TimeConverter::convertHoursToMinutes),
    MINUTES(TimeConverter::convertMinutesToMinutes);

    public final Function<String, Duration> durationConverter;

    public static TimeConverter findByValue(String value) {
        return stream(TimeConverter.values())
                .filter(operator -> operator.name().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private static Duration convertMinutesToMinutes(String value) {
        return Duration.ofMinutes(Long.parseLong(value));
    }

    private static Duration convertHoursToMinutes(String value) {
        return Duration.ofHours(Long.parseLong(value));
    }

}
