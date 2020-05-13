package nl.robinlaugs.kpnmovies.repository;

import nl.robinlaugs.kpnmovies.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
