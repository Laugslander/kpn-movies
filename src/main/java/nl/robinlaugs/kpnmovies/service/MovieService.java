package nl.robinlaugs.kpnmovies.service;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.Customer;
import nl.robinlaugs.kpnmovies.model.Movie;
import nl.robinlaugs.kpnmovies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final CustomerService customerService;
    private final MovieRepository movieRepository;

    public List<Movie> getSuggestions(String customerId) {
        Customer customer = customerService.findById(customerId);

        return movieRepository.findByInterests(customer.getInterests());
    }

}
