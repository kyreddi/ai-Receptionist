package com.aireceptionist.service.telephony;

import com.aireceptionist.domain.CallSession;
import com.aireceptionist.dto.WebhookDtos;

public interface TelephonyProvider {
    CallSession handleInbound(WebhookDtos.InboundCallRequest request);
    CallSession handleStatus(WebhookDtos.CallStatusRequest request);
    void sendSms(String to, String message);
}
