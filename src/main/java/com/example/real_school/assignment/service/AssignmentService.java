package com.example.real_school.assignment.service;

import com.example.real_school.assignment.dto.AssignmentDto;
import java.util.List;

public interface AssignmentService {
    List<AssignmentDto> getAssignmentsBySection(Long sectionId);
    AssignmentDto getAssignmentById(Long id);
    AssignmentDto createAssignment(AssignmentDto dto);
    AssignmentDto updateAssignment(Long id, AssignmentDto dto);
    void deleteAssignment(Long id);
}
