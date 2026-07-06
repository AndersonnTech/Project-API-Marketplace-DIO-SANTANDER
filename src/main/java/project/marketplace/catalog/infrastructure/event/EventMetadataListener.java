package project.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import project.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;

@Component
public class EventMetadataListener extends AbstractMongoEventListener<EventMetadata> {

    public static final Logger logger = LoggerFactory.getLogger(EventMetadataListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAAfterSave {}", event.getDocument());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata deleted via onAfterDelete {}", event.getDocument());
    }
}
