package com.aireceptionist.web;

import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.WebhookDtos;
import com.aireceptionist.repository.TenantRepository;
import com.aireceptionist.service.telephony.TelephonyProvider;
import com.aireceptionist.service.telephony.TelephonyProviderRegistry;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/telephony")
public class WebhookController {
    private final TelephonyProviderRegistry providerRegistry;
    private final TenantRepository tenantRepository;

    public WebhookController(TelephonyProviderRegistry providerRegistry, TenantRepository tenantRepository) {
        this.providerRegistry = providerRegistry;
        this.tenantRepository = tenantRepository;
    }

    @PostMapping("/inbound")
    public void inbound(@Valid @RequestBody WebhookDtos.InboundCallRequest request) {
        Tenant tenant = tenantRepository.findByPhoneNumber(request.tenantPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Unknown tenant phone"));
        TelephonyProvider provider = providerRegistry.getProvider(tenant.getProviderType());
        provider.handleInbound(request);
    }

    @PostMapping("/status")
    public void status(@Valid @RequestBody WebhookDtos.CallStatusRequest request) {
        TelephonyProvider provider = providerRegistry.getProvider("TWILIO");
        provider.handleStatus(request);
    }
}
