package nl.robinlaugs.kpnmovies.repository;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.Actor;
import nl.robinlaugs.kpnmovies.model.Gender;
import nl.robinlaugs.kpnmovies.model.Genre;
import nl.robinlaugs.kpnmovies.model.Interest;
import nl.robinlaugs.kpnmovies.model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.Duration;
import java.util.List;

import static nl.robinlaugs.kpnmovies.model.Gender.MALE;
import static nl.robinlaugs.kpnmovies.model.Genre.ACTION;
import static nl.robinlaugs.kpnmovies.model.Genre.COMEDY;
import static nl.robinlaugs.kpnmovies.model.Genre.THRILLER;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.ACTORS;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.GENDER;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.GENRES;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.RATINGS;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.RUNTIME;
import static nl.robinlaugs.kpnmovies.model.InterestOperator.GREATER_THEN_OR_EQUAL_TO;
import static nl.robinlaugs.kpnmovies.model.InterestOperator.LESS_THEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@DataMongoTest
@TestInstance(PER_CLASS)
@RequiredArgsConstructor
class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    private final Actor actor = Actor.builder()
            .name("name")
            .gender(MALE)
            .build();

    private final Movie movie = Movie.builder()
            .rating(8)
            .runtime(Duration.ofMinutes(120))
            .genres(List.of(COMEDY, ACTION))
            .actors(List.of(actor))
            .build();

    @BeforeAll
    public void setUp() {
        repository.save(movie);
    }

    @Test
    public void findByInterests_matchingActorGenderInterestAndNonGenresMatchingInterestForExistingMovie_returnsMovie() {
        Interest<Gender> matchingInterest = Interest.<Gender>builder()
                .category(GENDER)
                .value(MALE)
                .build();

        Interest<Genre> nonMatchingInterest = Interest.<Genre>builder()
                .category(GENRES)
                .value(THRILLER)
                .build();

        List<Interest<?>> interests = List.of(matchingInterest, nonMatchingInterest);

        Movie actual = repository.findByInterests(interests).get(0);

        assertEquals(movie, actual);
    }

    @Test
    public void findByInterests_matchingRatingsInterestForExistingMovie_returnsMovie() {
        Interest<Double> matchingInterest = Interest.<Double>builder()
                .category(RATINGS)
                .operator(GREATER_THEN_OR_EQUAL_TO)
                .value(8D)
                .build();

        List<Interest<?>> interests = List.of(matchingInterest);

        Movie actual = repository.findByInterests(interests).get(0);

        assertEquals(movie, actual);
    }

    @Test
    public void findByInterests_matchingRuntimeInterestForExistingMovie_returnsMovie() {
        Interest<Duration> matchingInterest = Interest.<Duration>builder()
                .category(RUNTIME)
                .operator(LESS_THEN)
                .value(Duration.ofMinutes(200))
                .build();

        List<Interest<?>> interests = List.of(matchingInterest);

        Movie actual = repository.findByInterests(interests).get(0);

        assertEquals(movie, actual);
    }

    @Test
    public void findByInterest_nonMatchingActorName_doesNotReturnMovie() {
        Interest<String> nonMatchingInterest = Interest.<String>builder()
                .category(ACTORS)
                .value("non matching name")
                .build();

        List<Interest<?>> interests = List.of(nonMatchingInterest);

        assertTrue(repository.findByInterests(interests).isEmpty());
    }

}