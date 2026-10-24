package com.example.real_school.hostel.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HostelRoomDto {
    private Long id;
    private Long blockId;
    private String roomNumber;
    private String roomType;
    private Integer capacity;
    private Integer occupied;
    private Double costPerMonth;
}
