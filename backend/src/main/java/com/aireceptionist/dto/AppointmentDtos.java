package com.aireceptionist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public class AppointmentDtos {
    public record AppointmentRequest(
            @NotBlank String name,
            @NotBlank String phone,
            @NotNull Instant startTime,
            @NotNull Instant endTime,
            @NotBlank String service,
            String notes
    ) {}

    public record AppointmentResponse(
            UUID id,
            String name,
            String phone,
            Instant startTime,
            Instant endTime,
            String service,
            String notes,
            String status
    ) {}
}
