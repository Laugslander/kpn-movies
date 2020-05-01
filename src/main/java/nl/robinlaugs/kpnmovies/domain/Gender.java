package nl.robinlaugs.kpnmovies.domain;

import java.util.Arrays;

public enum Gender {

    MALE,
    FEMALE;

    public static Gender findByValue(String value) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.toString().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
