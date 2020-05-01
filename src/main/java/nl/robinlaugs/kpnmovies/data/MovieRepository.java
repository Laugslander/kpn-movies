package nl.robinlaugs.kpnmovies.data;

import nl.robinlaugs.kpnmovies.domain.Gender;
import nl.robinlaugs.kpnmovies.domain.Genre;
import nl.robinlaugs.kpnmovies.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Duration;
import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByRating(double rating);

    List<Movie> findByRatingGreaterThanEqual(double rating);

    List<Movie> findByRatingLessThanEqual(double rating);

    List<Movie> findByRuntime(Duration runtime);

    List<Movie> findByRuntimeGreaterThan(Duration runtime);

    List<Movie> findByRuntimeLessThan(Duration runtime);

    List<Movie> findByGenresContaining(Genre genre);

    List<Movie> findByActorsName(String actorName);

    List<Movie> findByActorsGender(Gender actorGender);

}
