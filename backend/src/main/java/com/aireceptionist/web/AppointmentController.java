package com.aireceptionist.web;

import com.aireceptionist.dto.AppointmentDtos;
import com.aireceptionist.security.TenantContext;
import com.aireceptionist.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final TenantContext tenantContext;

    public AppointmentController(AppointmentService appointmentService, TenantContext tenantContext) {
        this.appointmentService = appointmentService;
        this.tenantContext = tenantContext;
    }

    @GetMapping
    public List<AppointmentDtos.AppointmentResponse> list() {
        return appointmentService.list(tenantContext.getTenantId());
    }

    @PostMapping
    public AppointmentDtos.AppointmentResponse create(@Valid @RequestBody AppointmentDtos.AppointmentRequest request) {
        return appointmentService.create(tenantContext.getTenantId(), request);
    }
}
