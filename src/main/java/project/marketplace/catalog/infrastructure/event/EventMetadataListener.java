package project.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;
import project.marketplace.common.infrastructure.event.dto.EventUpdated;

@Component
public class EventMetadataListener extends AbstractMongoEventListener<EventMetadata> {

    public static final Logger logger = LoggerFactory.getLogger(EventMetadataListener.class);

    private final ApplicationEventPublisher publisher;

    public EventMetadataListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAAfterSave {}", event.getDocument());
        publisher.publishEvent(EventUpdated.from(event.getSource()));
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata deleted via onAfterDelete {}", event.getDocument());
    }
}
