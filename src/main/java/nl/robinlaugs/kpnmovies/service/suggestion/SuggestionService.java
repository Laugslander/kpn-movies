package nl.robinlaugs.kpnmovies.service.suggestion;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.domain.Customer;
import nl.robinlaugs.kpnmovies.domain.Movie;
import nl.robinlaugs.kpnmovies.service.customer.CustomerNotFoundException;
import nl.robinlaugs.kpnmovies.service.customer.CustomerService;
import nl.robinlaugs.kpnmovies.service.movie.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final CustomerService customerService;
    private final MovieService movieService;

    public List<Movie> getSuggestions(String customerId) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomer(customerId);

        return customer.getInterests().entrySet().stream()
                .map(entry -> movieService.findByInterest(entry.getKey(), entry.getValue()))
                .flatMap(List::stream)
                .distinct()
                .collect(toList());
    }

}
