package com.example.real_school.security;

import com.example.real_school.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    public String generateToken(Authentication authentication) {
        // TODO: Implement JWT token generation
        return "";
    }

    public String getUsernameFromToken(String token) {
        // TODO: Implement username extraction from JWT
        return "";
    }

    public boolean validateToken(String token) {
        // TODO: Implement JWT token validation
        return false;
    }
}
