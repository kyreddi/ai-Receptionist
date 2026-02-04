package com.aireceptionist.service;

import com.aireceptionist.domain.Appointment;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.AppointmentDtos;
import com.aireceptionist.repository.AppointmentRepository;
import com.aireceptionist.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TenantRepository tenantRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, TenantRepository tenantRepository) {
        this.appointmentRepository = appointmentRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<AppointmentDtos.AppointmentResponse> list(UUID tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        return appointmentRepository.findByTenant(tenant).stream()
                .map(this::toResponse)
                .toList();
    }

    public AppointmentDtos.AppointmentResponse create(UUID tenantId, AppointmentDtos.AppointmentRequest request) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        ensureNoCollision(tenant, request.startTime(), request.endTime());
        Appointment appointment = new Appointment();
        appointment.setTenant(tenant);
        appointment.setName(request.name());
        appointment.setPhone(request.phone());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setService(request.service());
        appointment.setNotes(request.notes());
        appointment.setStatus("CONFIRMED");
        return toResponse(appointmentRepository.save(appointment));
    }

    private void ensureNoCollision(Tenant tenant, Instant start, Instant end) {
        List<Appointment> collisions = appointmentRepository
                .findByTenantAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(tenant, end, start);
        if (!collisions.isEmpty()) {
            throw new IllegalArgumentException("Appointment overlaps with existing booking");
        }
    }

    private AppointmentDtos.AppointmentResponse toResponse(Appointment appointment) {
        return new AppointmentDtos.AppointmentResponse(
                appointment.getId(),
                appointment.getName(),
                appointment.getPhone(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getService(),
                appointment.getNotes(),
                appointment.getStatus()
        );
    }
}
