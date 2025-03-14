package com.hacom.hacom_app.dtos.trace_msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TraceMsgResponse {
    private String id;
    private String sessionId;
    private String payload;
    private OffsetDateTime ts;
}
