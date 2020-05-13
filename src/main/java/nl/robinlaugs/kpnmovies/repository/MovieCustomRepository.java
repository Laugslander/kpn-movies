package nl.robinlaugs.kpnmovies.repository;

import nl.robinlaugs.kpnmovies.model.Interest;
import nl.robinlaugs.kpnmovies.model.Movie;

import java.util.List;

public interface MovieCustomRepository {

    List<Movie> findByInterests(List<Interest<?>> interests);

}
