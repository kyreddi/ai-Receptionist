package com.aireceptionist.service;

import com.aireceptionist.domain.Role;
import com.aireceptionist.domain.Tenant;
import com.aireceptionist.domain.UserAccount;
import com.aireceptionist.dto.AuthDtos;
import com.aireceptionist.repository.TenantRepository;
import com.aireceptionist.repository.UserAccountRepository;
import com.aireceptionist.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {
    private final TenantRepository tenantRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            TenantRepository tenantRepository,
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.tenantRepository = tenantRepository;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthDtos.AuthResponse signup(AuthDtos.SignupRequest request) {
        Tenant tenant = new Tenant();
        tenant.setName(request.tenantName());
        tenant.setTimezone(request.timezone());
        tenant.setPhoneNumber(request.phoneNumber());
        tenant.setProviderType("TWILIO");
        tenant.setProviderConfigJson("{}");
        tenant.setCreatedAt(Instant.now());
        Tenant savedTenant = tenantRepository.save(tenant);

        UserAccount user = new UserAccount();
        user.setTenant(savedTenant);
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(Role.OWNER);
        user.setCreatedAt(Instant.now());
        UserAccount savedUser = userAccountRepository.save(user);

        String token = jwtService.createToken(savedUser.getId(), savedTenant.getId(), savedUser.getRole(), savedUser.getEmail());
        return new AuthDtos.AuthResponse(token, savedTenant.getId(), savedUser.getId(), savedUser.getRole());
    }

    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        UserAccount user = userAccountRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = jwtService.createToken(user.getId(), user.getTenant().getId(), user.getRole(), user.getEmail());
        return new AuthDtos.AuthResponse(token, user.getTenant().getId(), user.getId(), user.getRole());
    }
}
