package com.hacom.hacom_app.dtos.trace_msg;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateRangeRequest {
    @NotNull
    private OffsetDateTime from;

    @NotNull
    private OffsetDateTime to;
}
