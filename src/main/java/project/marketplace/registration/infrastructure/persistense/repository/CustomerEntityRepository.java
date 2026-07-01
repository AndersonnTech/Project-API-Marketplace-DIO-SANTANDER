package project.marketplace.registration.infrastructure.persistense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import project.marketplace.registration.infrastructure.persistense.entity.Customer;
import project.marketplace.registration.infrastructure.persistense.entity.projection.CustomerExcerpt;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = CustomerExcerpt.class)
public interface CustomerEntityRepository extends PagingAndSortingRepository<Customer, UUID>, CrudRepository<Customer, UUID> {
    List<Customer> findByFirstNameStartingWithIgnoreCase(@Param("FirstName") String firstName);

    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
}
