package project.marketplace.ticketing.infrastructure.http.request;

import project.marketplace.ticketing.domain.SeatId;

public record SeatSelectionRequest(String id) {
    public SeatId toInput(){
        return new SeatId(id);
    }
}
