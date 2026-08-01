package com.example.real_school.student.service.impl;

import com.example.real_school.student.service.ParentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    @Override
    public List<ParentDto> getAllParents() {
        return Collections.emptyList();
    }
    @Override
    public ParentDto getParentById(Long id) {
        return null;
    }
    @Override
    public ParentDto createParent(ParentDto parentDto) {
        return null;
    }
    @Override
    public ParentDto updateParent(Long id, ParentDto parentDto) {
        return null;
    }
    @Override
    public List<StudentDto> getChildren(Long id) {
        return Collections.emptyList();
    }
}
