package com.aireceptionist.service;

import com.aireceptionist.domain.BusinessProfile;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.BusinessProfileDtos;
import com.aireceptionist.repository.BusinessProfileRepository;
import com.aireceptionist.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BusinessProfileService {
    private final BusinessProfileRepository businessProfileRepository;
    private final TenantRepository tenantRepository;

    public BusinessProfileService(BusinessProfileRepository businessProfileRepository, TenantRepository tenantRepository) {
        this.businessProfileRepository = businessProfileRepository;
        this.tenantRepository = tenantRepository;
    }

    public BusinessProfileDtos.BusinessProfileResponse get(UUID tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        BusinessProfile profile = businessProfileRepository.findByTenant(tenant)
                .orElseGet(() -> {
                    BusinessProfile newProfile = new BusinessProfile();
                    newProfile.setTenant(tenant);
                    newProfile.setHoursJson("{}");
                    newProfile.setServicesJson("[]");
                    newProfile.setPoliciesText("");
                    return businessProfileRepository.save(newProfile);
                });
        return new BusinessProfileDtos.BusinessProfileResponse(
                profile.getId(),
                profile.getHoursJson(),
                profile.getServicesJson(),
                profile.getPoliciesText()
        );
    }

    public BusinessProfileDtos.BusinessProfileResponse update(UUID tenantId, BusinessProfileDtos.BusinessProfileRequest request) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        BusinessProfile profile = businessProfileRepository.findByTenant(tenant)
                .orElseGet(() -> {
                    BusinessProfile newProfile = new BusinessProfile();
                    newProfile.setTenant(tenant);
                    return newProfile;
                });
        profile.setHoursJson(request.hoursJson());
        profile.setServicesJson(request.servicesJson());
        profile.setPoliciesText(request.policiesText());
        BusinessProfile saved = businessProfileRepository.save(profile);
        return new BusinessProfileDtos.BusinessProfileResponse(
                saved.getId(),
                saved.getHoursJson(),
                saved.getServicesJson(),
                saved.getPoliciesText()
        );
    }
}
