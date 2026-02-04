package com.aireceptionist.dto;

import jakarta.validation.constraints.NotBlank;

public class WebhookDtos {
    public record InboundCallRequest(
            @NotBlank String providerCallId,
            @NotBlank String fromNumber,
            @NotBlank String toNumber,
            @NotBlank String tenantPhoneNumber
    ) {}

    public record CallStatusRequest(
            @NotBlank String providerCallId,
            @NotBlank String status,
            String recordingUrl
    ) {}

    public record SimulatorMessageRequest(
            @NotBlank String tenantId,
            @NotBlank String message
    ) {}

    public record SimulatorMessageResponse(
            String response,
            String callSessionId
    ) {}
}
