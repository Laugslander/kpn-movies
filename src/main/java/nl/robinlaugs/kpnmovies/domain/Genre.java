package nl.robinlaugs.kpnmovies.domain;

import java.util.Arrays;

public enum Genre {

    ACTION,
    THRILLER,
    COMEDY,
    DRAMA;

    public static Genre findByValue(String value) {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.toString().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
