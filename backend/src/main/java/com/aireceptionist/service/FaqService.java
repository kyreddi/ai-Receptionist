package com.aireceptionist.service;

import com.aireceptionist.domain.Faq;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.dto.FaqDtos;
import com.aireceptionist.repository.FaqRepository;
import com.aireceptionist.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FaqService {
    private final FaqRepository faqRepository;
    private final TenantRepository tenantRepository;

    public FaqService(FaqRepository faqRepository, TenantRepository tenantRepository) {
        this.faqRepository = faqRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<FaqDtos.FaqResponse> list(UUID tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        return faqRepository.findByTenant(tenant).stream()
                .map(faq -> new FaqDtos.FaqResponse(faq.getId(), faq.getQuestion(), faq.getAnswer()))
                .toList();
    }

    public FaqDtos.FaqResponse create(UUID tenantId, FaqDtos.FaqRequest request) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        Faq faq = new Faq();
        faq.setTenant(tenant);
        faq.setQuestion(request.question());
        faq.setAnswer(request.answer());
        Faq saved = faqRepository.save(faq);
        return new FaqDtos.FaqResponse(saved.getId(), saved.getQuestion(), saved.getAnswer());
    }

    public FaqDtos.FaqResponse update(UUID tenantId, UUID id, FaqDtos.FaqRequest request) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        Faq faq = faqRepository.findByIdAndTenant(id, tenant).orElseThrow();
        faq.setQuestion(request.question());
        faq.setAnswer(request.answer());
        Faq saved = faqRepository.save(faq);
        return new FaqDtos.FaqResponse(saved.getId(), saved.getQuestion(), saved.getAnswer());
    }

    public void delete(UUID tenantId, UUID id) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
        Faq faq = faqRepository.findByIdAndTenant(id, tenant).orElseThrow();
        faqRepository.delete(faq);
    }
}
