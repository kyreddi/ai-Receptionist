package com.aireceptionist.web;

import com.aireceptionist.dto.FaqDtos;
import com.aireceptionist.security.TenantContext;
import com.aireceptionist.service.FaqService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/faqs")
public class FaqController {
    private final FaqService faqService;
    private final TenantContext tenantContext;

    public FaqController(FaqService faqService, TenantContext tenantContext) {
        this.faqService = faqService;
        this.tenantContext = tenantContext;
    }

    @GetMapping
    public List<FaqDtos.FaqResponse> list() {
        return faqService.list(tenantContext.getTenantId());
    }

    @PostMapping
    public FaqDtos.FaqResponse create(@Valid @RequestBody FaqDtos.FaqRequest request) {
        return faqService.create(tenantContext.getTenantId(), request);
    }

    @PutMapping("/{id}")
    public FaqDtos.FaqResponse update(@PathVariable UUID id, @Valid @RequestBody FaqDtos.FaqRequest request) {
        return faqService.update(tenantContext.getTenantId(), id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        faqService.delete(tenantContext.getTenantId(), id);
    }
}
