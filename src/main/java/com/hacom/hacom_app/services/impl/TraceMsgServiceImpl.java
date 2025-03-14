package com.hacom.hacom_app.services.impl;

import com.hacom.hacom_app.dtos.trace_msg.TraceMsgRequest;
import com.hacom.hacom_app.dtos.trace_msg.TraceMsgResponse;
import com.hacom.hacom_app.entities.TraceMsg;
import com.hacom.hacom_app.repositories.TraceMsgRepository;
import com.hacom.hacom_app.services.TraceMsgService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TraceMsgServiceImpl implements TraceMsgService {
    private static final Logger logger = LogManager.getLogger(TraceMsgServiceImpl.class);

    private final TraceMsgRepository repository;

    @Override
    public Mono<TraceMsgResponse> saveTraceMsg(TraceMsgRequest req) {
        logger.info("Request recibido {}", req);
        TraceMsg traceMsg = TraceMsg.builder()
                ._id(new ObjectId())
                .sessionId(req.getSessionId())
                .payload(req.getPayload())
                .ts(req.getTs().toInstant())
                .build();
        logger.info("Entidad a gaurdar {}", traceMsg);
        Mono<TraceMsg> saved = repository.save(traceMsg);
        logger.info("Guardado en base de datos con éxito {}", saved);
        return saved.map(this::toResponse);
    }

    @Override
    public Flux<TraceMsgResponse> getTraceMsgsByDateRange(OffsetDateTime from, OffsetDateTime to) {
        logger.info("from recibido {}", from);
        logger.info("to recibido {}", to);
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
