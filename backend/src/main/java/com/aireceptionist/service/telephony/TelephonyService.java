package com.aireceptionist.service.telephony;

import com.aireceptionist.domain.CallSession;
import com.aireceptionist.domain.CallStatus;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.WebhookDtos;
import com.aireceptionist.repository.CallSessionRepository;
import com.aireceptionist.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TelephonyService {
    private final TenantRepository tenantRepository;
    private final CallSessionRepository callSessionRepository;

    public TelephonyService(TenantRepository tenantRepository, CallSessionRepository callSessionRepository) {
        this.tenantRepository = tenantRepository;
        this.callSessionRepository = callSessionRepository;
    }

    public CallSession handleInbound(WebhookDtos.InboundCallRequest request) {
        Tenant tenant = tenantRepository.findByPhoneNumber(request.tenantPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Unknown tenant phone"));
        CallSession session = new CallSession();
        session.setTenant(tenant);
        session.setProviderCallId(request.providerCallId());
        session.setFromNumber(request.fromNumber());
        session.setToNumber(request.toNumber());
        session.setStatus(CallStatus.RINGING);
        session.setStartedAt(Instant.now());
        return callSessionRepository.save(session);
    }

    public CallSession handleStatus(WebhookDtos.CallStatusRequest request) {
        CallSession session = callSessionRepository.findByProviderCallId(request.providerCallId())
                .orElseThrow(() -> new IllegalArgumentException("Call session not found"));
        if (request.status().equalsIgnoreCase("in_progress")) {
            session.setStatus(CallStatus.IN_PROGRESS);
        } else if (request.status().equalsIgnoreCase("ended")) {
            session.setStatus(CallStatus.ENDED);
            session.setEndedAt(Instant.now());
        }
        if (request.recordingUrl() != null) {
            session.setRecordingUrl(request.recordingUrl());
        }
        return callSessionRepository.save(session);
    }
}
