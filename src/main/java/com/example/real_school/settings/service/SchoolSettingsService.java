package com.example.real_school.settings.service;

import com.example.real_school.settings.dto.SchoolSettingsDto;

public interface SchoolSettingsService {

    /** Public — the current school name/logo/tagline shown across the app. */
    SchoolSettingsDto getSettings();

    /** Admin only — update the school name, logo and profile. */
    SchoolSettingsDto updateSettings(SchoolSettingsDto dto);
}