package com.example.real_school.assignment.service.impl;

import com.example.real_school.academic.entity.Section;
import com.example.real_school.academic.entity.Subject;
import com.example.real_school.academic.repository.SectionRepository;
import com.example.real_school.academic.repository.SubjectRepository;
import com.example.real_school.assignment.dto.AssignmentDto;
import com.example.real_school.assignment.entity.Assignment;
import com.example.real_school.assignment.repository.AssignmentRepository;
import com.example.real_school.assignment.service.AssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 SectionRepository sectionRepository,
                                 SubjectRepository subjectRepository) {
        this.assignmentRepository = assignmentRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDto> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDto> getAssignmentsBySection(Long sectionId) {
        return assignmentRepository.findBySectionId(sectionId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AssignmentDto getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));
        return mapToDto(assignment);
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDto dto) {
        if (dto.getSectionId() == null) {
            throw new IllegalArgumentException("sectionId must not be null");
        }
        if (dto.getSubjectId() == null) {
            throw new IllegalArgumentException("subjectId must not be null");
        }

        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + dto.getSectionId()));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + dto.getSubjectId()));

        Assignment assignment = Assignment.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .fileUrl(dto.getFileUrl())
                .section(section)
                .subject(subject)
                .build();

        return mapToDto(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto updateAssignment(Long id, AssignmentDto dto) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));

        if (dto.getSectionId() != null) {
            Section section = sectionRepository.findById(dto.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with ID: " + dto.getSectionId()));
            assignment.setSection(section);
        }

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + dto.getSubjectId()));
            assignment.setSubject(subject);
        }

        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setFileUrl(dto.getFileUrl());

        return mapToDto(assignmentRepository.save(assignment));
    }

    @Override
    public void deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new RuntimeException("Assignment not found with ID: " + id);
        }
        assignmentRepository.deleteById(id);
    }

    private AssignmentDto mapToDto(Assignment assignment) {
        return AssignmentDto.builder()
                .id(assignment.getId())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .sectionId(assignment.getSection() != null ? assignment.getSection().getId() : null)
                .subjectId(assignment.getSubject() != null ? assignment.getSubject().getId() : null)
                .fileUrl(assignment.getFileUrl())
                .build();
    }
}