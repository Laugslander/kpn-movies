package nl.robinlaugs.kpnmovies.model;

import lombok.Builder;
import lombok.Data;

import static nl.robinlaugs.kpnmovies.model.InterestOperator.NONE;

@Data
@Builder
public class Interest<T> {

    private final InterestCategory category;

    @Builder.Default
    private final InterestOperator operator = NONE;

    private final T value;

}
