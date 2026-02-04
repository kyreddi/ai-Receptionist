package com.aireceptionist.repository;

import com.aireceptionist.domain.CallSession;
import com.aireceptionist.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CallSessionRepository extends JpaRepository<CallSession, UUID> {
    List<CallSession> findByTenant(Tenant tenant);
    Optional<CallSession> findByIdAndTenant(UUID id, Tenant tenant);
    Optional<CallSession> findByProviderCallId(String providerCallId);
}
