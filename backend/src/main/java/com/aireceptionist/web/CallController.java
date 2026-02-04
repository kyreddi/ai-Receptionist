package com.aireceptionist.web;

import com.aireceptionist.dto.CallDtos;
import com.aireceptionist.security.TenantContext;
import com.aireceptionist.service.CallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/calls")
public class CallController {
    private final CallService callService;
    private final TenantContext tenantContext;

    public CallController(CallService callService, TenantContext tenantContext) {
        this.callService = callService;
        this.tenantContext = tenantContext;
    }

    @GetMapping
    public List<CallDtos.CallSummary> list() {
        return callService.list(tenantContext.getTenantId());
    }

    @GetMapping("/{id}")
    public CallDtos.CallDetail get(@PathVariable UUID id) {
        return callService.get(tenantContext.getTenantId(), id);
    }
}
