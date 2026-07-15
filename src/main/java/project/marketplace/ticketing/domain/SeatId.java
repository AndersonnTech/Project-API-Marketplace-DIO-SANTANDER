package project.marketplace.ticketing.domain;

import lombok.Getter;
import org.springframework.util.Assert;

public record SeatId(String id){
    public SeatId {
        Assert.notNull(id, "id must not be null");
    }
}
