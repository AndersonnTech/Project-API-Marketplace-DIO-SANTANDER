package project.marketplace.catalog.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import project.marketplace.catalog.domain.*;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class MongoEventMetadataRepository implements EventMetadataRepository {

    private EventMetaDataEntityRepository eventMetaDataEntityRepository;

    public MongoEventMetadataRepository(EventMetaDataEntityRepository eventMetaDataEntityRepository) {
        this.eventMetaDataEntityRepository = eventMetaDataEntityRepository;
    }

    @Override
    public Optional<EventMetadata> findByEventId(EventId eventId) {
        return eventMetaDataEntityRepository.findByEventId(eventId.id()).map(MongoEventMetadataRepository::mapper);
    }

    public static EventMetadata mapper (project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata eventMetadata) {
        var sectors = eventMetadata.getSectors().stream()
                .map(MongoEventMetadataRepository::mapper)
                .collect(Collectors.toMap(
                        sector -> sector.getId().nome(),
                        Function.identity()
                ));
        var  seats = eventMetadata.getSeats().stream()
                .map(MongoEventMetadataRepository::mapper)
                .collect(Collectors.groupingBy(
                        seat -> sectors.get(seat.getSectorId().nome())
                ));

        return new EventMetadata(
                eventMetadata.getEventDescription(),
                eventMetadata.getTechnicalRequirements(),
                seats);


    }

    public static Seat mapper (project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata.Seat seat) {
        return new Seat(new SeatId(seat.getCode()), new SectorId(seat.getSectorName()));
    }

    public static Sector mapper (project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata.Sector sector) {
        return new Sector(new SectorId(sector.getName()), sector.getPrice());
    }
}
