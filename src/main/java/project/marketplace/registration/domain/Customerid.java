package project.marketplace.registration.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record Customerid (UUID id) {

    public Customerid{
        Assert.notNull(id, "id must not be null");
    }

    public Customerid(){
        this(UUID.randomUUID());
    }
}
