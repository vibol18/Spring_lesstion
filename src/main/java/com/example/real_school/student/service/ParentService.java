package com.example.real_school.student.service;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import java.util.List;

public interface ParentService {
    List<ParentDto> getAllParents();
    ParentDto getParentById(Long id);
    ParentDto createParent(ParentDto parentDto);
    ParentDto updateParent(Long id, ParentDto parentDto);
    List<StudentDto> getChildren(Long id);
}
