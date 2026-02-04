package com.aireceptionist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String timezone;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String providerType;

    @Column(nullable = false, columnDefinition = "text")
    private String providerConfigJson;

    @Column(nullable = false)
    private Instant createdAt;
}
