package com.example.real_school.settings.controller;

import com.example.real_school.settings.dto.SchoolSettingsDto;
import com.example.real_school.settings.service.SchoolSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school/settings")
@RequiredArgsConstructor
@CrossOrigin
public class SchoolSettingsController {

    private final SchoolSettingsService schoolSettingsService;

    /** Public — the school name/logo shown on login, register, sidebar and the site header. */
    @GetMapping
    public SchoolSettingsDto getSettings() {
        return schoolSettingsService.getSettings();
    }

    /** Admin only — updates the name/logo everywhere in the app. */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public SchoolSettingsDto updateSettings(@RequestBody SchoolSettingsDto dto) {
        return schoolSettingsService.updateSettings(dto);
    }
}