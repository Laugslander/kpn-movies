package nl.robinlaugs.kpnmovies.service.movie;

import nl.robinlaugs.kpnmovies.data.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Duration;

class MovieServiceTest {

    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService service;

    public MovieServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByRatings_ratingContainingNoOperator_invokesRepositoryFindByRating() {
        service.findByRatings("7");

        Mockito.verify(repository).findByRating(7);
    }

    @Test
    public void findByRatings_ratingContainingPlusOperator_invokesRepositoryFindByRatingGreaterThanEqual() {
        service.findByRatings("8+");

        Mockito.verify(repository).findByRatingGreaterThanEqual(8);
    }

    @Test
    public void findByRatings_ratingContainingMinusOperator_jnvokesRepositoryFindByRatingLessThanEqual() {
        service.findByRatings("9-");

        Mockito.verify(repository).findByRatingLessThanEqual(9);
    }

    @Test
    public void findByRuntime_runtimeMinutesContainingNoOperator_invokesRepositoryFindByRuntime() {
        service.findByRuntime("60 minutes");

        Mockito.verify(repository).findByRuntime(Duration.ofMinutes(60));
    }

    @Test
    public void findByRuntime_runtimeHoursContainingNoOperator_invokesRepositoryFindByRuntime() {
        service.findByRuntime("2 hours");

        Mockito.verify(repository).findByRuntime(Duration.ofHours(2));
    }

    @Test
    public void findByRuntime_runtimeMinutesContainingGreaterThanOperator_invokesRepositoryFindByRuntimeGreaterThan() {
        service.findByRuntime("> 120 minutes");

        Mockito.verify(repository).findByRuntimeGreaterThan(Duration.ofMinutes(120));
    }

    @Test
    public void findByRuntime_runtimeHoursContainingGreaterThanOperator_invokesRepositoryFindByRuntimeGreaterThan() {
        service.findByRuntime("> 2 hours");

        Mockito.verify(repository).findByRuntimeGreaterThan(Duration.ofHours(2));
    }

    @Test
    public void findByRuntime_runtimeMinutesContainingLessThanOperator_invokesRepositoryFindByRuntimeLessThan() {
        service.findByRuntime("< 120 minutes");

        Mockito.verify(repository).findByRuntimeLessThan(Duration.ofMinutes(120));
    }

    @Test
    public void findByRuntime_runtimeHoursContainingLessThanOperator_invokesRepositoryFindByRuntimeLessThen() {
        service.findByRuntime("< 2 hours");

        Mockito.verify(repository).findByRuntimeLessThan(Duration.ofHours(2));
    }

}