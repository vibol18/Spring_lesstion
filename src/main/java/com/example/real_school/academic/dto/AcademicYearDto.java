package com.example.real_school.academic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearDto {
    private Long id;
    private String yearName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean current;
}
