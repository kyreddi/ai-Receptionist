package com.aireceptionist.service.ai;

import java.util.List;

public interface LlmClient {
    String generateResponse(String context, List<String> messages);
}
