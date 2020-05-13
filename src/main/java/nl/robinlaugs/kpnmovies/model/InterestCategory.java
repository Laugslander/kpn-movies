package nl.robinlaugs.kpnmovies.model;

import lombok.RequiredArgsConstructor;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
public enum InterestCategory {

    RATINGS,
    RUNTIME,
    GENRES,
    ACTORS,
    GENDER;

    public static InterestCategory findByValue(String value) {
        return stream(InterestCategory.values())
                .filter(category -> category.name().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
