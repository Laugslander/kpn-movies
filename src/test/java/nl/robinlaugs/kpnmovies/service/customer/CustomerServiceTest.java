package nl.robinlaugs.kpnmovies.service.customer;

import nl.robinlaugs.kpnmovies.data.CustomerRepository;
import nl.robinlaugs.kpnmovies.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    public CustomerServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void getCustomer_knownId_returnsAssociatedCustomer() throws CustomerNotFoundException {
        Customer customer = Customer.builder()
                .id("1")
                .build();

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(customer));

        Assertions.assertEquals(customer, service.getCustomer("1"));
    }

    @Test
    public void getCustomer_unknownId_throwsCustomerNotFoundException() {
        Mockito.when(repository.findById("1")).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> service.getCustomer("1"));
    }

}