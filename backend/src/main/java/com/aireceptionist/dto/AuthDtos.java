package com.aireceptionist.dto;

import com.aireceptionist.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AuthDtos {
    public record SignupRequest(
            @NotBlank String tenantName,
            @NotBlank String timezone,
            @NotBlank String phoneNumber,
            @NotBlank String email,
            @NotBlank String password
    ) {}

    public record LoginRequest(
            @Email @NotBlank String email,
            @NotBlank String password
    ) {}

    public record AuthResponse(
            String token,
            UUID tenantId,
            UUID userId,
            Role role
    ) {}
}
