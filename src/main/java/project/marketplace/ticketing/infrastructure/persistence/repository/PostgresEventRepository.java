package project.marketplace.ticketing.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import project.marketplace.ticketing.domain.Event;
import project.marketplace.ticketing.domain.EventRepository;
import project.marketplace.ticketing.domain.Seat;
import project.marketplace.ticketing.domain.Sector;

import java.util.List;

@Repository
public class PostgresEventRepository implements EventRepository {
    private EventCrudRepository eventCrudRepository;

    public PostgresEventRepository(EventCrudRepository eventCrudRepository) {
        this.eventCrudRepository = eventCrudRepository;
    }

    @Override
    public void save(Event event) {
        var sectors = event.getSeats().entrySet().stream()
                .map(entry -> {
                    Sector domainSector = entry.getKey();
                    List<Seat> domainSeats = entry.getValue();

                    var seats = domainSeats.stream()
                            .map(s -> new project.marketplace.ticketing.infrastructure.persistence.entity.Seat(
                                    s.getId(),
                                    s.getCorrelationId().id()
                            ))
                            .toList();
                    return new project.marketplace.ticketing.infrastructure.persistence.entity.Sector(
                            domainSector.getId(),
                            domainSector.getCorrelationId().id(),
                            domainSector.getPrice(),
                            seats
                    );
                })
                .toList();

        var entity = new project.marketplace.ticketing.infrastructure.persistence.entity.Event(
                event.getId(),
                event.getCorrelationId().id(),
                sectors);

        eventCrudRepository.save(entity);
    }
}
