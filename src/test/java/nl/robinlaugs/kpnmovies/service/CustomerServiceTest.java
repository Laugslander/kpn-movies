package nl.robinlaugs.kpnmovies.service;

import nl.robinlaugs.kpnmovies.model.Customer;
import nl.robinlaugs.kpnmovies.repository.CustomerRepository;
import nl.robinlaugs.kpnmovies.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    public CustomerServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void getCustomer_knownId_returnsAssociatedCustomer() {
        Customer customer = Customer.builder()
                .id("1")
                .build();

        Mockito.when(customerRepository.findById("1")).thenReturn(Optional.of(customer));

        assertEquals(customer, customerService.findById("1"));
    }

    @Test
    public void getCustomer_unknownId_throwsCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("0")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById("0"));
    }

}