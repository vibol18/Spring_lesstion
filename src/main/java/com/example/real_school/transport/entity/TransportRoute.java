package com.example.real_school.transport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transport_routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startLocation;
    private String endLocation;
    private String vehicleNumber;
    private String driverName;
    private String driverContact;
}
