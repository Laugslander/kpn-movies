package nl.robinlaugs.kpnmovies.repository.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.InterestCategory;

@RequiredArgsConstructor
public enum MovieKey {

    RATINGS("rating"),
    RUNTIME("runtime"),
    GENRES("genres"),
    ACTORS("actors.name"),
    GENDER("actors.gender");

    @Getter
    private final String key;

    public static MovieKey from(InterestCategory interestCategory) {
        return MovieKey.valueOf(interestCategory.toString());
    }

}
