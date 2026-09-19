package com.example.real_school.config;

import com.example.real_school.user.entity.Role;
import com.example.real_school.user.entity.User;
import com.example.real_school.user.repository.RoleRepository;
import com.example.real_school.user.repository.UserRepository;
import com.example.real_school.settings.entity.SchoolSettings;
import com.example.real_school.settings.repository.SchoolSettingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SchoolSettingsRepository schoolSettingsRepository;

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.email:admin@school.com}")
    private String adminEmail;

    @Value("${app.admin.password:Admin123!}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        List<String> roleNames = List.of(
                Role.USER,
                Role.ADMIN,
                Role.TEACHER,
                Role.STUDENT,
                Role.PARENT,
                Role.STAFF,
                Role.LIBRARIAN,
                Role.ACCOUNTANT
        );

        for (String name : roleNames) {
            if (!roleRepository.findByName(name).isPresent()) {
                roleRepository.save(Role.builder()
                        .name(name)
                        .description(name.toLowerCase() + " role")
                        .build());
                log.info("Seeded role: {}", name);
            }
        }

        seedDefaultAdmin();
        seedDefaultSchoolSettings();
    }

    /** Creates the single school_settings row (id=1) so the public name/logo
     *  endpoint always has something to return. The admin can change it later. */
    private void seedDefaultSchoolSettings() {
        if (schoolSettingsRepository.existsById(1L)) {
            return;
        }
        schoolSettingsRepository.save(SchoolSettings.builder()
                .id(1L)
                .name("EduCore School")
                .shortName("EC")
                .tagline("Manage your entire school in one place")
                .build());
        log.info("Seeded default school settings");
    }

    private void seedDefaultAdmin() {
        if (userRepository.existsByUsername(adminUsername)) {
            return;
        }
        if (userRepository.existsByEmail(adminEmail)) {
            log.warn("Skipped seeding admin: email {} is already registered", adminEmail);
            return;
        }
        Role adminRole = roleRepository.findByName(Role.ADMIN)
                .orElseThrow(() -> new IllegalStateException("ADMIN role missing, can't seed admin user"));
        userRepository.save(User.builder()
                .username(adminUsername)
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .role(adminRole)
                .isActive(true)
                .build());
        log.info("Seeded default admin user: {}", adminUsername);
    }
}
