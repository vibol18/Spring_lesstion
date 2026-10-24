package com.example.real_school.transport.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransportStopDto {
    private Long id;
    private Long routeId;
    private String stopName;
    private Double fare;
}
