package nl.robinlaugs.kpnmovies.service;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.Customer;
import nl.robinlaugs.kpnmovies.repository.CustomerRepository;
import nl.robinlaugs.kpnmovies.service.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s was not found", id)));
    }

}
