package nl.robinlaugs.kpnmovies.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Actor {

    private String name;
    private Gender gender;

}
