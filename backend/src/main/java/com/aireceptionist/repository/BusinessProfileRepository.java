package com.aireceptionist.repository;

import com.aireceptionist.domain.BusinessProfile;
import com.aireceptionist.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, UUID> {
    Optional<BusinessProfile> findByTenant(Tenant tenant);
}
