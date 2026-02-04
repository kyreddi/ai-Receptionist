package com.aireceptionist.service;

import com.aireceptionist.domain.CallMessage;
import com.aireceptionist.domain.CallMessageSender;
import com.aireceptionist.domain.CallSession;
import com.aireceptionist.domain.CallStatus;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.CallDtos;
import com.aireceptionist.repository.CallMessageRepository;
import com.aireceptionist.repository.CallSessionRepository;
import com.aireceptionist.repository.TenantRepository;
import com.aireceptionist.service.ai.LlmClient;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CallService {
    private final CallSessionRepository callSessionRepository;
    private final CallMessageRepository callMessageRepository;
    private final TenantRepository tenantRepository;
    private final LlmClient llmClient;

    public CallService(
            CallSessionRepository callSessionRepository,
            CallMessageRepository callMessageRepository,
            TenantRepository tenantRepository,
            LlmClient llmClient
    ) {
        this.callSessionRepository = callSessionRepository;
        this.callMessageRepository = callMessageRepository;
        this.tenantRepository = tenantRepository;
        this.llmClient = llmClient;
    }

    public List<CallDtos.CallSummary> list(UUID tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        return callSessionRepository.findByTenant(tenant).stream()
                .map(session -> new CallDtos.CallSummary(
                        session.getId(),
                        session.getFromNumber(),
                        session.getToNumber(),
                        session.getStatus(),
                        session.getStartedAt(),
                        session.getEndedAt()
                ))
                .toList();
    }

    public CallDtos.CallDetail get(UUID tenantId, UUID id) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        CallSession session = callSessionRepository.findByIdAndTenant(id, tenant).orElseThrow();
        List<CallDtos.CallMessageResponse> messages = callMessageRepository
                .findByCallSessionOrderByCreatedAt(session)
                .stream()
                .map(message -> new CallDtos.CallMessageResponse(
                        message.getId(),
                        message.getSender().name(),
                        message.getText(),
                        message.getCreatedAt()
                ))
                .toList();
        return new CallDtos.CallDetail(
                session.getId(),
                session.getProviderCallId(),
                session.getFromNumber(),
                session.getToNumber(),
                session.getStatus(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getRecordingUrl(),
                session.getSummary(),
                session.getIntent(),
                session.getConfidence(),
                messages
        );
    }

    public String addSimulatorMessage(UUID tenantId, String message) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        CallSession session = new CallSession();
        session.setTenant(tenant);
        session.setProviderCallId("SIMULATOR-" + Instant.now().toEpochMilli());
        session.setFromNumber("SIMULATOR");
        session.setToNumber(tenant.getPhoneNumber());
        session.setStatus(CallStatus.IN_PROGRESS);
        session.setStartedAt(Instant.now());
        CallSession saved = callSessionRepository.save(session);

        CallMessage inbound = new CallMessage();
        inbound.setCallSession(saved);
        inbound.setSender(CallMessageSender.CALLER);
        inbound.setText(message);
        inbound.setCreatedAt(Instant.now());
        callMessageRepository.save(inbound);

        String response = llmClient.generateResponse("", List.of(message));

        CallMessage aiMessage = new CallMessage();
        aiMessage.setCallSession(saved);
        aiMessage.setSender(CallMessageSender.AI);
        aiMessage.setText(response);
        aiMessage.setCreatedAt(Instant.now());
        callMessageRepository.save(aiMessage);

        return response;
    }
}
