package project.marketplace.catalog.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource
public interface EventMetaDataEntityRepository extends MongoRepository<EventMetadata, String> {
    Optional<EventMetadata> findByEventId(UUID eventId);
}
