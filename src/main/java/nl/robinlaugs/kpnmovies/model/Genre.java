package nl.robinlaugs.kpnmovies.model;

import static java.util.Arrays.stream;

public enum Genre {

    ACTION,
    THRILLER,
    COMEDY,
    DRAMA;

    public static Genre findByValue(String value) {
        return stream(values())
                .filter(genre -> genre.name().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
