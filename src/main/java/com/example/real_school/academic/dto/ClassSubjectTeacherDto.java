package com.example.real_school.academic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubjectTeacherDto {
    private Long id;
    private Long sectionId;
    private Long subjectId;
    private Long teacherId;
}
