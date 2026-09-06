package com.example.real_school.config;

import com.example.real_school.user.entity.Role;
import com.example.real_school.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> roleNames = List.of(
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
    }
}
