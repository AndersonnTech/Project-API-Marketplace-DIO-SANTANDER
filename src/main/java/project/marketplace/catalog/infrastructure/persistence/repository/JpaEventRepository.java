package project.marketplace.catalog.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import project.marketplace.catalog.domain.EventId;
import project.marketplace.catalog.domain.EventRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaEventRepository implements EventRepository {

    private final EventEntityRepository eventEntityRepository;

    public JpaEventRepository(EventEntityRepository eventEntityRepository) {
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public List<project.marketplace.catalog.domain.Event> findAll() {
        var iterable = eventEntityRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaEventRepository::mapper).toList();
    }

    private static project.marketplace.catalog.domain.Event mapper(project.marketplace.catalog.infrastructure.persistence.entity.Event event) {
        return new project.marketplace.catalog.domain.Event(
                new EventId(event.getId()),
                event.getTitle(),
                event.getDate(),
                Optional.empty()
                );
    }
}
