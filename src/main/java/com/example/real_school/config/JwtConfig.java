package com.example.real_school.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {
    private String secret = "defaultSecretKeyThatShouldBeChangedInProduction12345678";
    private long expirationMs = 86400000; // 24 hours
    private String tokenPrefix = "Bearer ";
    private String headerString = "Authorization";
}
