package project.marketplace.ticketing.infrastructure.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.marketplace.ticketing.infrastructure.persistence.entity.Customer;

import java.util.UUID;
@RepositoryRestResource(exported = false, path = "_customers")
public interface CustomerCrudRepository extends CrudRepository<Customer, UUID> {
}
