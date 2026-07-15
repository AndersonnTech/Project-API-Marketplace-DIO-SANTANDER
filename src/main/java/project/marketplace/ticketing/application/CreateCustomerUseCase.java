package project.marketplace.ticketing.application;

import org.springframework.stereotype.Service;
import project.marketplace.common.infrastructure.event.dto.CustomerCreated;
import project.marketplace.ticketing.domain.Customer;
import project.marketplace.ticketing.domain.CustomerRepository;

@Service
public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(CustomerCreated event) {
        var customer = new Customer(event.id(), event.name());
        customerRepository.save(customer);
    }
}
