package com.example.real_school.student.service.impl;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.entity.Parent;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.ParentRepository;
import com.example.real_school.student.service.ParentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public List<ParentDto> getAllParents() {
        return parentRepository.findAll().stream()
                .map(this::mapToParentDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParentDto getParentById(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + id));
        return mapToParentDto(parent);
    }

    @Override
    public ParentDto createParent(ParentDto parentDto) {
        Parent parent = Parent.builder()
                .occupation(parentDto.getOccupation())
                .contactNumber(parentDto.getContactNumber())
                .address(parentDto.getAddress())
                .build();

        Parent savedParent = parentRepository.save(parent);
        return mapToParentDto(savedParent);
    }

    @Override
    public ParentDto updateParent(Long id, ParentDto parentDto) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + id));

        parent.setOccupation(parentDto.getOccupation());
        parent.setContactNumber(parentDto.getContactNumber());
        parent.setAddress(parentDto.getAddress());

        Parent updatedParent = parentRepository.save(parent);
        return mapToParentDto(updatedParent);
    }

    @Override
    public List<StudentDto> getChildren(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + id));

        return parent.getChildren().stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }

    // Helper Mappers
    private ParentDto mapToParentDto(Parent parent) {
        ParentDto dto = new ParentDto();
        dto.setId(parent.getId());
        dto.setOccupation(parent.getOccupation());
        dto.setContactNumber(parent.getContactNumber());
        dto.setAddress(parent.getAddress());
        return dto;
    }

    private StudentDto mapToStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setAdmissionNumber(student.getAdmissionNumber());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setBloodGroup(student.getBloodGroup());
        dto.setAddress(student.getAddress());
        return dto;
    }
}