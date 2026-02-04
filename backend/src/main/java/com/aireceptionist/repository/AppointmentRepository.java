package com.aireceptionist.repository;

import com.aireceptionist.domain.Appointment;
import com.aireceptionist.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByTenant(Tenant tenant);
    List<Appointment> findByTenantAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Tenant tenant,
            Instant startTime,
            Instant endTime
    );
}
