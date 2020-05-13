package nl.robinlaugs.kpnmovies.service;

import nl.robinlaugs.kpnmovies.model.Customer;
import nl.robinlaugs.kpnmovies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class MovieServiceTest {

    @Mock
    private CustomerService customerService;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    public MovieServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void getSuggestions_knownCustomersId_callsMovieRepository() {
        Customer customer = Customer.builder()
                .id("1")
                .build();

        Mockito.when(customerService.findById("1")).thenReturn(customer);

        movieService.getSuggestions("1");

        Mockito.verify(movieRepository).findByInterests(customer.getInterests());
    }

}