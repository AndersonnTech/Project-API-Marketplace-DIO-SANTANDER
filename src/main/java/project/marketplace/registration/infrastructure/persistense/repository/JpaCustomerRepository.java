package project.marketplace.registration.infrastructure.persistense.repository;

import org.springframework.stereotype.Repository;
import project.marketplace.registration.domain.Customer;
import project.marketplace.registration.domain.CustomerRepository;
import project.marketplace.registration.domain.Customerid;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private final CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaCustomerRepository::mapper).toList();
    }

    private static project.marketplace.registration.infrastructure.persistense.entity.Customer mapper (Customer customer){
        var entity = new project.marketplace.registration.infrastructure.persistense.entity.Customer();

        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());

        return entity;
    }

    private static Customer mapper(project.marketplace.registration.infrastructure.persistense.entity.Customer entity){
        String fullname = Optional.ofNullable(entity.getLastName())
                .map(lastName -> entity.getFirstName() + " " + lastName)
                .orElseGet(entity::getFirstName);
        return new Customer(new Customerid(entity.getId()), fullname, entity.getEmail());
    }
}

