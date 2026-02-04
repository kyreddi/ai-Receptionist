package com.aireceptionist.repository;

import com.aireceptionist.domain.Faq;
import com.aireceptionist.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FaqRepository extends JpaRepository<Faq, UUID> {
    List<Faq> findByTenant(Tenant tenant);
    Optional<Faq> findByIdAndTenant(UUID id, Tenant tenant);
}
