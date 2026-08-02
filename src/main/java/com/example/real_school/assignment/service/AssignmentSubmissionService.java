package com.example.real_school.assignment.service;

import com.example.real_school.assignment.dto.AssignmentSubmissionDto;
import java.util.List;

public interface AssignmentSubmissionService {
    AssignmentSubmissionDto submitAssignment(AssignmentSubmissionDto dto);
    List<AssignmentSubmissionDto> getSubmissionsByAssignment(Long assignmentId);
    List<AssignmentSubmissionDto> getSubmissionsByStudent(Long studentId);
    AssignmentSubmissionDto gradeSubmission(Long id, Double marks, String feedback);
}
