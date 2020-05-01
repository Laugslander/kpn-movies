package nl.robinlaugs.kpnmovies.data;

import nl.robinlaugs.kpnmovies.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
