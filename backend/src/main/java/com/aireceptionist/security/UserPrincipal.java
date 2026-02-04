package com.aireceptionist.security;

import com.aireceptionist.domain.Role;

import java.util.UUID;

public record UserPrincipal(UUID userId, UUID tenantId, Role role, String email) {
}
