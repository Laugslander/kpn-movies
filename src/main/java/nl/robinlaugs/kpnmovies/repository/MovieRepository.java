package nl.robinlaugs.kpnmovies.repository;

import nl.robinlaugs.kpnmovies.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String>, MovieCustomRepository {
}
