package com.example.real_school.settings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO as consumed by the frontend school brand:
 * { id, name, shortName, logoUrl, tagline, address, phone, email, establishedYear, updatedAt }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolSettingsDto {

    private Long id;
    private String name;
    private String shortName;
    private String logoUrl;
    private String tagline;
    private String address;
    private String phone;
    private String email;
    private Integer establishedYear;
    private LocalDateTime updatedAt;
}