package com.example.real_school.transport.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportRouteDto {
    private Long id;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private String vehicleNumber;
    private String driverName;
    private String driverContact;
}
