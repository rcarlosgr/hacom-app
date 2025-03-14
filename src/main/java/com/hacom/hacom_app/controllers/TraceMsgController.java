package com.hacom.hacom_app.controllers;

import com.hacom.hacom_app.dtos.trace_msg.DateRangeRequest;
import com.hacom.hacom_app.dtos.trace_msg.TraceMsgRequest;
import com.hacom.hacom_app.dtos.trace_msg.TraceMsgResponse;
import com.hacom.hacom_app.services.TraceMsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/trace-msgs")
@RequiredArgsConstructor
public class TraceMsgController {
    private final TraceMsgService service;

    @PostMapping
    public Mono<ResponseEntity<TraceMsgResponse>> createTraceMsg(@RequestBody @Validated TraceMsgRequest req) {
        return service.saveTraceMsg(req)
                .map(saved -> ResponseEntity.created(URI.create("/api/trace-msgs/" + saved.getId())).body(saved));
    }

    @GetMapping("/by-date-range")
    public Flux<TraceMsgResponse> getTraceMsgsByDateRange(@RequestBody @Validated DateRangeRequest dateRange) {
        return service.getTraceMsgsByDateRange(dateRange.getFrom(), dateRange.getTo());
    }
}
