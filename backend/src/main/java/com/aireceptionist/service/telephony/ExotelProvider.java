package com.aireceptionist.service.telephony;

import com.aireceptionist.domain.CallSession;
import com.aireceptionist.dto.WebhookDtos;
import org.springframework.stereotype.Component;

@Component("EXOTEL")
public class ExotelProvider implements TelephonyProvider {
    private final TelephonyService telephonyService;

    public ExotelProvider(TelephonyService telephonyService) {
        this.telephonyService = telephonyService;
    }

    @Override
    public CallSession handleInbound(WebhookDtos.InboundCallRequest request) {
        return telephonyService.handleInbound(request);
    }

    @Override
    public CallSession handleStatus(WebhookDtos.CallStatusRequest request) {
        return telephonyService.handleStatus(request);
    }

    @Override
    public void sendSms(String to, String message) {
        // Stub for Exotel SMS
    }
}
