package com.example.real_school.hostel.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HostelAllocationDto {
    private Long id;
    private Long roomId;
    private Long studentId;
    private LocalDate allocationDate;
    private LocalDate vacantDate;
    private Boolean isActive;
}
