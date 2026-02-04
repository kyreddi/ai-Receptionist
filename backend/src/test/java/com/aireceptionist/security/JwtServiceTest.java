package com.aireceptionist.security;

import com.aireceptionist.domain.Role;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {
    @Test
    void createsAndParsesToken() {
        JwtService jwtService = new JwtService(
                "test-secret-test-secret-test-secret",
                "test",
                60
        );
        UUID userId = UUID.randomUUID();
        UUID tenantId = UUID.randomUUID();
        String token = jwtService.createToken(userId, tenantId, Role.OWNER, "owner@example.com");
        UserPrincipal principal = jwtService.parseToken(token);
        assertThat(principal.userId()).isEqualTo(userId);
        assertThat(principal.tenantId()).isEqualTo(tenantId);
        assertThat(principal.role()).isEqualTo(Role.OWNER);
        assertThat(principal.email()).isEqualTo("owner@example.com");
    }
}
