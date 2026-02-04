package com.aireceptionist.web;

import com.aireceptionist.dto.WebhookDtos;
import com.aireceptionist.service.CallService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/simulator")
public class SimulatorController {
    private final CallService callService;

    public SimulatorController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping("/chat")
    public WebhookDtos.SimulatorMessageResponse chat(@Valid @RequestBody WebhookDtos.SimulatorMessageRequest request) {
        UUID tenantId = UUID.fromString(request.tenantId());
        String response = callService.addSimulatorMessage(tenantId, request.message());
        return new WebhookDtos.SimulatorMessageResponse(response, "simulated");
    }
}
