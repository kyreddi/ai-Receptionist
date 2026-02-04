package com.aireceptionist.dto;

import com.aireceptionist.domain.CallStatus;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class CallDtos {
    public record CallSummary(
            UUID id,
            String fromNumber,
            String toNumber,
            CallStatus status,
            Instant startedAt,
            Instant endedAt
    ) {}

    public record CallMessageResponse(
            UUID id,
            String sender,
            String text,
            Instant createdAt
    ) {}

    public record CallDetail(
            UUID id,
            String providerCallId,
            String fromNumber,
            String toNumber,
            CallStatus status,
            Instant startedAt,
            Instant endedAt,
            String recordingUrl,
            String summary,
            String intent,
            Double confidence,
            List<CallMessageResponse> messages
    ) {}
}
