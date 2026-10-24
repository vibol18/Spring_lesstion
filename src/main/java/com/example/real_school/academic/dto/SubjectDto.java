package com.example.real_school.academic.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private String code;
    private List<Long> teacherIds = new ArrayList<>();
}