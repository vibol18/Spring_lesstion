package com.example.real_school.assignment.service.impl;

import com.example.real_school.assignment.dto.AssignmentSubmissionDto;
import com.example.real_school.assignment.entity.Assignment;
import com.example.real_school.assignment.entity.AssignmentSubmission;
import com.example.real_school.assignment.repository.AssignmentRepository;
import com.example.real_school.assignment.repository.AssignmentSubmissionRepository;
import com.example.real_school.assignment.service.AssignmentSubmissionService;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    public AssignmentSubmissionServiceImpl(AssignmentSubmissionRepository submissionRepository,
                                           AssignmentRepository assignmentRepository,
                                           StudentRepository studentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public AssignmentSubmissionDto submitAssignment(AssignmentSubmissionDto dto) {
        AssignmentSubmission submission = new AssignmentSubmission();
        
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        submission.setAssignment(assignment);
        
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        submission.setStudent(student);
        
        submission.setFileUrl(dto.getFileUrl());
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus("SUBMITTED");
        
        return mapToDto(submissionRepository.save(submission));
    }

    @Override
    public List<AssignmentSubmissionDto> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findAll().stream()
                .filter(s -> s.getAssignment() != null && s.getAssignment().getId().equals(assignmentId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentSubmissionDto> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findAll().stream()
                .filter(s -> s.getStudent() != null && s.getStudent().getId().equals(studentId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentSubmissionDto gradeSubmission(Long id, Double marks, String feedback) {
        AssignmentSubmission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        
        submission.setMarks(marks);
        submission.setFeedback(feedback);
        submission.setStatus("GRADED");
        
        return mapToDto(submissionRepository.save(submission));
    }
    
    private AssignmentSubmissionDto mapToDto(AssignmentSubmission sub) {
        AssignmentSubmissionDto dto = new AssignmentSubmissionDto();
        dto.setId(sub.getId());
        dto.setAssignmentId(sub.getAssignment().getId());
        dto.setStudentId(sub.getStudent().getId());
        dto.setFileUrl(sub.getFileUrl());
        dto.setSubmittedAt(sub.getSubmittedAt());
        dto.setMarks(sub.getMarks());
        dto.setFeedback(sub.getFeedback());
        dto.setStatus(sub.getStatus());
        return dto;
    }
}
