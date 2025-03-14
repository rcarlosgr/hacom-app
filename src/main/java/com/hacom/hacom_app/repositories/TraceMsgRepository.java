package com.hacom.hacom_app.repositories;

import com.hacom.hacom_app.entities.TraceMsg;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg, ObjectId> {
    Flux<TraceMsg> findByTsBetween(Instant from, Instant to);
}
