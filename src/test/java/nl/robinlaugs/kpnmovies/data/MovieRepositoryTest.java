package nl.robinlaugs.kpnmovies.data;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.domain.Actor;
import nl.robinlaugs.kpnmovies.domain.Gender;
import nl.robinlaugs.kpnmovies.domain.Genre;
import nl.robinlaugs.kpnmovies.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.Duration;
import java.util.List;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor
class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    private final Actor actor = Actor.builder()
            .name("name")
            .gender(Gender.MALE)
            .build();

    private final Movie movie = Movie.builder()
            .rating(8)
            .runtime(Duration.ofMinutes(120))
            .genres(List.of(Genre.COMEDY, Genre.ACTION))
            .actors(List.of(actor))
            .build();

    @BeforeAll
    public void setUp() {
        repository.save(movie);
    }

    @Test
    public void findByRating() {
        Assertions.assertEquals(movie, repository.findByRating(8).get(0));
    }

    @Test
    public void findByRatingGreaterThanEqual() {
        Assertions.assertEquals(movie, repository.findByRatingGreaterThanEqual(7).get(0));
    }

    @Test
    public void findByRatingLessThanEqual() {
        Assertions.assertEquals(movie, repository.findByRatingLessThanEqual(10).get(0));
    }

    @Test
    public void findByRuntime() {
        Assertions.assertEquals(movie, repository.findByRuntime(Duration.ofMinutes(120)).get(0));
    }

    @Test
    public void findByRuntimeGreaterThan() {
        Assertions.assertEquals(movie, repository.findByRuntimeGreaterThan(Duration.ofMinutes(100)).get(0));
    }

    @Test
    public void findByRuntimeLessThan() {
        Assertions.assertEquals(movie, repository.findByRuntimeLessThan(Duration.ofMinutes(140)).get(0));
    }

    @Test
    public void findByGenresContaining() {
        Assertions.assertEquals(movie, repository.findByGenresContaining(Genre.COMEDY).get(0));
    }

    @Test
    public void findByActorsName() {
        Assertions.assertEquals(movie, repository.findByActorsName("name").get(0));
    }

    @Test
    public void findByActorsGender() {
        Assertions.assertEquals(movie, repository.findByActorsGender(Gender.MALE).get(0));
    }

}