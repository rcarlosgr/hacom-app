package com.hacom.hacom_app.services;

import com.hacom.hacom_app.dtos.trace_msg.TraceMsgRequest;
import com.hacom.hacom_app.dtos.trace_msg.TraceMsgResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

public interface TraceMsgService {
    Mono<TraceMsgResponse> saveTraceMsg(TraceMsgRequest req);
    Flux<TraceMsgResponse> getTraceMsgsByDateRange(OffsetDateTime from, OffsetDateTime to);
}
