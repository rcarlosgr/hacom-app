package com.hacom.hacom_app.dtos.trace_msg;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TraceMsgRequest {
    @NotBlank
    private String sessionId;
    @NotBlank
    private String payload;
    @NotNull
    private OffsetDateTime ts;
}
