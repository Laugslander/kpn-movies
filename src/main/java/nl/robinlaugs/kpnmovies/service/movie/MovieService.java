package nl.robinlaugs.kpnmovies.service.movie;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.data.MovieRepository;
import nl.robinlaugs.kpnmovies.domain.Gender;
import nl.robinlaugs.kpnmovies.domain.Genre;
import nl.robinlaugs.kpnmovies.domain.Interest;
import nl.robinlaugs.kpnmovies.domain.Movie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findByInterest(Interest interest, String value) {
        return switch (interest) {
            case RATINGS -> findByRatings(value);
            case RUNTIME -> findByRuntime(value);
            case GENRES -> findByGenres(value);
            case ACTORS -> findByActorNames(value);
            case GENDER -> findByActorGender(value);
        };
    }

    List<Movie> findByRatings(String value) {
        String operatorValue = value.substring(value.length() - 1);

        RatingOperator operator = RatingOperator.findByValue(operatorValue);

        double rating = operator.ratingExtractor.apply(value);

        return operator.movieFinder.apply(movieRepository, rating);
    }

    List<Movie> findByRuntime(String value) {
        String[] valueAttributes = value.split(" ");
        String operatorValue = valueAttributes[0];

        RuntimeOperator operator = RuntimeOperator.findByValue(operatorValue);

        Duration runtime = operator.runtimeExtractor.apply(valueAttributes);

        return operator.movieFinder.apply(movieRepository, runtime);
    }

    List<Movie> findByGenres(String value) {
        Genre genre = Genre.findByValue(value);

        return movieRepository.findByGenresContaining(genre);
    }

    List<Movie> findByActorNames(String value) {
        return movieRepository.findByActorsName(value);
    }

    List<Movie> findByActorGender(String value) {
        Gender gender = Gender.findByValue(value);

        return movieRepository.findByActorsGender(gender);
    }

}
