package nl.robinlaugs.kpnmovies.domain;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Interest {

    RATINGS,
    RUNTIME,
    GENRES,
    ACTORS,
    GENDER;

    public static Interest findByValue(String value) {
        return Arrays.stream(Interest.values())
                .filter(interest -> interest.toString().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
