package com.aireceptionist.web;

import com.aireceptionist.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {
    @GetMapping("/me")
    public UserPrincipal me(@AuthenticationPrincipal UserPrincipal principal) {
        return principal;
    }
}
