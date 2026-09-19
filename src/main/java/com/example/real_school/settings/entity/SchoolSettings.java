package com.example.real_school.settings.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "school_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolSettings {

    /** Single-row settings table — always uses id = 1. */
    @Id
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    /** Short abbreviation / initials shown inside the logo box when no logo image exists. */
    @Column(length = 20)
    private String shortName;

    @Lob
    @Column(name = "logo_url")
    private String logoUrl;

    @Column(length = 200)
    private String tagline;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String phone;

    @Column(length = 150)
    private String email;

    private Integer establishedYear;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    protected void onSave() {
        this.updatedAt = LocalDateTime.now();
    }
}