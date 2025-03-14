package com.hacom.hacom_app.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "trace_msgs")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TraceMsg {
    @Id
    private ObjectId _id;
    private String sessionId;
    private String payload;
    private Instant ts;
}
