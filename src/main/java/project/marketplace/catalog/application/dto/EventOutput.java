package project.marketplace.catalog.application.dto;

import project.marketplace.catalog.domain.Event;
import project.marketplace.catalog.domain.EventMetadata;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record EventOutput(
        String id,
        String title,
        Instant date,
        EventMetadataOutput metadata
) {
    public record EventMetadataOutput(
            String eventDescription,
            Map<String, Object> technicalRequirements,
            Map<String, List<SeatOutput>> seatsBySector
    ) {
        public record SeatOutput(
                String id,
                String sectorId,
                BigDecimal price
        ){
        }

        public static EventMetadataOutput from(EventMetadata metadata) {
            Map<String, List<SeatOutput>> seats =
                    metadata.seats().entrySet().stream()
                            .collect(Collectors.toMap(
                                    entry -> entry.getKey().getId().nome(),
                                    entry -> entry.getValue().stream()
                                            .map(seat -> new EventOutput.EventMetadataOutput.SeatOutput(
                                                    seat.getId().seatNumber(),
                                                    seat.getSectorId().nome(),
                                                    entry.getKey().getPrice()
                                            ))
                                            .toList()
                                    ));
            return new EventOutput.EventMetadataOutput(
                    metadata.eventDescription(),
                    metadata.technicalRequirements(),
                    seats
            );
        }
    }

    public static EventOutput from(Event event) {
        return new EventOutput(
                event.getId().id().toString(),
                event.getTitle(),
                event.getDate(),
                event.getMetadata()
                        .map(EventMetadataOutput::from)
                        .orElse(null)
        );
    }
}
