package nl.robinlaugs.kpnmovies.model;

import static java.util.Arrays.stream;

public enum Gender {

    MALE,
    FEMALE;

    public static Gender findByValue(String value) {
        return stream(values())
                .filter(gender -> gender.name().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
