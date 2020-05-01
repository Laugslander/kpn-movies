package nl.robinlaugs.kpnmovies.service.customer;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.data.CustomerRepository;
import nl.robinlaugs.kpnmovies.domain.Customer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomer(String id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s was not found", id)));
    }

}
