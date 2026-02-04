package com.aireceptionist.service.ai;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenAiClient implements LlmClient {
    @Override
    public String generateResponse(String context, List<String> messages) {
        return "OpenAI integration placeholder";
    }
}
