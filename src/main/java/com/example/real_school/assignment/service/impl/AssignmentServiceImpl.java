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
    public List<AssignmentDto> getAssignmentsBySection(Long sectionId) {
        return assignmentRepository.findAll().stream()
                .filter(a -> a.getSection() != null && a.getSection().getId().equals(sectionId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDto getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));
        return mapToDto(assignment);
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDto dto) {
        Assignment assignment = new Assignment();
        
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));
        assignment.setSection(section);
        
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        assignment.setSubject(subject);
        
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setFileUrl(dto.getFileUrl());
        
        return mapToDto(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto updateAssignment(Long id, AssignmentDto dto) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));
                
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));
        assignment.setSection(section);
        
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        assignment.setSubject(subject);
        
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
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        if (assignment.getSection() != null) dto.setSectionId(assignment.getSection().getId());
        if (assignment.getSubject() != null) dto.setSubjectId(assignment.getSubject().getId());
        dto.setFileUrl(assignment.getFileUrl());
        return dto;
    }
}
