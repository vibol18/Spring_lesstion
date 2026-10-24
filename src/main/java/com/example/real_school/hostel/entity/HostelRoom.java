package com.example.real_school.hostel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hostel_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private HostelBlock hostelBlock;

    private String roomNumber;
    private String roomType; // AC, NON_AC, SINGLE, DOUBLE...
    private Integer capacity;
    private Integer occupied;
    private Double costPerMonth;
}
