package com.aireceptionist.web;

import com.aireceptionist.dto.BusinessProfileDtos;
import com.aireceptionist.security.TenantContext;
import com.aireceptionist.service.BusinessProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business-profile")
public class BusinessProfileController {
    private final BusinessProfileService businessProfileService;
    private final TenantContext tenantContext;

    public BusinessProfileController(BusinessProfileService businessProfileService, TenantContext tenantContext) {
        this.businessProfileService = businessProfileService;
        this.tenantContext = tenantContext;
    }

    @GetMapping
    public BusinessProfileDtos.BusinessProfileResponse get() {
        return businessProfileService.get(tenantContext.getTenantId());
    }

    @PutMapping
    public BusinessProfileDtos.BusinessProfileResponse update(@Valid @RequestBody BusinessProfileDtos.BusinessProfileRequest request) {
        return businessProfileService.update(tenantContext.getTenantId(), request);
    }
}
