package nl.robinlaugs.kpnmovies.repository.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.InterestCategory;

@RequiredArgsConstructor
public enum MongoMovieKey {

    RATINGS("rating"),
    RUNTIME("runtime"),
    GENRES("genres"),
    ACTORS("actors.name"),
    GENDER("actors.gender");

    @Getter
    private final String key;

    public static MongoMovieKey from(InterestCategory interestCategory) {
        return MongoMovieKey.valueOf(interestCategory.toString());
    }

}
