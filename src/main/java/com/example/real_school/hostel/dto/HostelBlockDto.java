package com.example.real_school.hostel.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HostelBlockDto {
    private Long id;
    private String name;
    private String type;        // BOYS, GIRLS
    private String description;
}
