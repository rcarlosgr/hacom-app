package com.hacom.hacom_app.services.impl;

import com.hacom.hacom_app.dtos.trace_msg.TraceMsgRequest;
import com.hacom.hacom_app.dtos.trace_msg.TraceMsgResponse;
import com.hacom.hacom_app.entities.TraceMsg;
import com.hacom.hacom_app.repositories.TraceMsgRepository;
import com.hacom.hacom_app.services.TraceMsgService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TraceMsgServiceImpl implements TraceMsgService {
    private final TraceMsgRepository repository;

    @Override
    public Mono<TraceMsgResponse> saveTraceMsg(TraceMsgRequest req) {
        TraceMsg traceMsg = TraceMsg.builder()
                ._id(new ObjectId())
                .sessionId(req.getSessionId())
                .payload(req.getPayload())
                .ts(req.getTs().toInstant())
                .build();

        return repository.save(traceMsg)
                .map(this::toResponse);
    }

    @Override
    public Flux<TraceMsgResponse> getTraceMsgsByDateRange(OffsetDateTime from, OffsetDateTime to) {
        return repository.findByTsBetween(from.toInstant(), to.toInstant())
                .map(this::toResponse);
    }

    private TraceMsgResponse toResponse(TraceMsg traceMsg) {
        return TraceMsgResponse.builder()
                .id(traceMsg.get_id().toHexString())
                .sessionId(traceMsg.getSessionId())
                .payload(traceMsg.getPayload())
                .ts(OffsetDateTime.ofInstant(traceMsg.getTs(), ZoneOffset.UTC))
                .build();
    }
}
