package com.aireceptionist.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class BusinessProfileDtos {
    public record BusinessProfileRequest(
            @NotBlank String hoursJson,
            @NotBlank String servicesJson,
            @NotBlank String policiesText
    ) {}

    public record BusinessProfileResponse(
            UUID id,
            String hoursJson,
            String servicesJson,
            String policiesText
    ) {}
}
