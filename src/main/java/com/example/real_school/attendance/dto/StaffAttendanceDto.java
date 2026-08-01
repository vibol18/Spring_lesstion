package com.example.real_school.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffAttendanceDto {
    private Long id;
    private Long staffId;
    private LocalDate attendanceDate;
    private String status;
    private String remark;
}
