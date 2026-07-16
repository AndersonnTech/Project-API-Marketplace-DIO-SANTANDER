package project.marketplace.ticketing.infrastructure.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.marketplace.ticketing.application.SelectSeatUseCase;
import project.marketplace.ticketing.domain.CustomerId;
import project.marketplace.ticketing.domain.EventId;
import project.marketplace.ticketing.infrastructure.http.request.SeatSelectionRequest;

@RestController
@RequestMapping("/ticketing/events/{eventId}/seats")
public class SeatSelectionController {
    private final SelectSeatUseCase selectSeatUseCase;

    public SeatSelectionController(SelectSeatUseCase selectSeatUseCase) {
        this.selectSeatUseCase = selectSeatUseCase;
    }

    @PostMapping("/select")
    @ResponseStatus(HttpStatus.CREATED)
    public void selectSeat(@PathVariable String eventId, @RequestBody SeatSelectionRequest request, @RequestHeader("X-CUSTOMER-ID") String customerId) {
        selectSeatUseCase.execute(new EventId(eventId), request.toInput(), new CustomerId(customerId));
    }
}
