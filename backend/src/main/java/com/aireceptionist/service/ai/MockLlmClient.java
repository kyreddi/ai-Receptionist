package com.aireceptionist.service.ai;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class MockLlmClient implements LlmClient {
    @Override
    public String generateResponse(String context, List<String> messages) {
        if (messages.isEmpty()) {
            return "Hello! How can I help you today?";
        }
        String last = messages.get(messages.size() - 1);
        return "Thanks for sharing: \"" + last + "\". Can you provide more details?";
    }
}
