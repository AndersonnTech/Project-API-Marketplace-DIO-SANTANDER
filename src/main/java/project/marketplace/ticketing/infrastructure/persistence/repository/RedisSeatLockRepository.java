package project.marketplace.ticketing.infrastructure.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import project.marketplace.ticketing.infrastructure.persistence.entity.SeatLock;

import java.util.Optional;

public interface RedisSeatLockRepository extends CrudRepository<SeatLock, String> {
    Optional<SeatLock> findByCustomerId(String customerId);
}
