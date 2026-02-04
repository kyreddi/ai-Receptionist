package com.aireceptionist.repository;

import com.aireceptionist.domain.CallMessage;
import com.aireceptionist.domain.CallSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CallMessageRepository extends JpaRepository<CallMessage, UUID> {
    List<CallMessage> findByCallSessionOrderByCreatedAt(CallSession callSession);
}
