package com.example.real_school.transport.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentTransportDto {
    private Long id;
    private Long studentId;
    private Long routeId;
    private Long stopId;
}
