package com.example.real_school.settings.service.impl;

import com.example.real_school.settings.dto.SchoolSettingsDto;
import com.example.real_school.settings.entity.SchoolSettings;
import com.example.real_school.settings.repository.SchoolSettingsRepository;
import com.example.real_school.settings.service.SchoolSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolSettingsServiceImpl implements SchoolSettingsService {

    /** Single-row settings entity — we always read/write id = 1. */
    static final Long SETTINGS_ID = 1L;

    private final SchoolSettingsRepository settingsRepository;

    @Override
    public SchoolSettingsDto getSettings() {
        return mapToDto(getOrCreate());
    }

    @Override
    public SchoolSettingsDto updateSettings(SchoolSettingsDto dto) {
        SchoolSettings settings = getOrCreate();
        settings.setName(dto.getName());
        if (dto.getShortName() != null) settings.setShortName(dto.getShortName());
        if (dto.getLogoUrl() != null) settings.setLogoUrl(dto.getLogoUrl());
        if (dto.getTagline() != null) settings.setTagline(dto.getTagline());
        if (dto.getAddress() != null) settings.setAddress(dto.getAddress());
        if (dto.getPhone() != null) settings.setPhone(dto.getPhone());
        if (dto.getEmail() != null) settings.setEmail(dto.getEmail());
        if (dto.getEstablishedYear() != null) settings.setEstablishedYear(dto.getEstablishedYear());
        return mapToDto(settingsRepository.save(settings));
    }

    private SchoolSettings getOrCreate() {
        return settingsRepository.findById(SETTINGS_ID).orElseGet(() -> {
            SchoolSettings defaults = SchoolSettings.builder()
                    .id(SETTINGS_ID)
                    .name("EduCore School")
                    .shortName("EC")
                    .tagline("Manage your entire school in one place")
                    .build();
            try {
                return settingsRepository.save(defaults);
            } catch (Exception ignore) {
                // Another request may have just created the row — read it back.
                return settingsRepository.findById(SETTINGS_ID).orElse(defaults);
            }
        });
    }

    private SchoolSettingsDto mapToDto(SchoolSettings s) {
        return SchoolSettingsDto.builder()
                .id(s.getId())
                .name(s.getName())
                .shortName(s.getShortName())
                .logoUrl(s.getLogoUrl())
                .tagline(s.getTagline())
                .address(s.getAddress())
                .phone(s.getPhone())
                .email(s.getEmail())
                .establishedYear(s.getEstablishedYear())
                .updatedAt(s.getUpdatedAt())
                .build();
    }
}