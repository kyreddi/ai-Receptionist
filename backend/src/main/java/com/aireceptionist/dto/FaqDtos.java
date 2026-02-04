package com.aireceptionist.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class FaqDtos {
    public record FaqRequest(
            @NotBlank String question,
            @NotBlank String answer
    ) {}

    public record FaqResponse(
            UUID id,
            String question,
            String answer
    ) {}
}
