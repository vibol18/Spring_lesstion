package com.example.real_school.hostel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hostel_blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Block A, Block B...
    private String type; // BOYS, GIRLS
    private String description;
}
