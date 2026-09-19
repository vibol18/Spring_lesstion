package com.example.real_school.settings.repository;

import com.example.real_school.settings.entity.SchoolSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolSettingsRepository extends JpaRepository<SchoolSettings, Long> {
}