package com.example.real_school.transport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transport_stops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private TransportRoute transportRoute;

    private String stopName;
    private Double fare;
}
