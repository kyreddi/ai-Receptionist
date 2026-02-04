package com.aireceptionist.repository;

import com.aireceptionist.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Optional<Tenant> findByPhoneNumber(String phoneNumber);
}
