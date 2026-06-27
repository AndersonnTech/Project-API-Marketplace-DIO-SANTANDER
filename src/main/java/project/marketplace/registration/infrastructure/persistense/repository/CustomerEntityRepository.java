package project.marketplace.registration.infrastructure.persistense.repository;

import org.springframework.data.repository.CrudRepository;
import project.marketplace.registration.infrastructure.persistense.entity.Customer;

import java.util.UUID;

public interface CustomerEntityRepository extends CrudRepository<Customer, UUID> {
}
