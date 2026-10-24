    package com.example.real_school.exam.service.impl;

import com.example.real_school.exam.dto.ExamResultDto;
import com.example.real_school.exam.entity.ExamResult;
import com.example.real_school.exam.entity.ExamSchedule;
import com.example.real_school.exam.repository.ExamResultRepository;
import com.example.real_school.exam.repository.ExamScheduleRepository;
import com.example.real_school.exam.service.ExamResultService;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository resultRepository;
    private final ExamScheduleRepository scheduleRepository;
    private final StudentRepository studentRepository;

    public ExamResultServiceImpl(ExamResultRepository resultRepository,
                                 ExamScheduleRepository scheduleRepository,
                                 StudentRepository studentRepository) {
        this.resultRepository = resultRepository;
        this.scheduleRepository = scheduleRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<ExamResultDto> getResultsByStudent(Long studentId) {
        return resultRepository.findAll().stream()
                .filter(r -> r.getStudent() != null && r.getStudent().getId().equals(studentId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultDto> getResultsByExamAndSubject(Long examId, Long subjectId) {
        return resultRepository.findAll().stream()
                .filter(r -> r.getExamSchedule() != null &&
                        r.getExamSchedule().getExam().getId().equals(examId) &&
                        r.getExamSchedule().getSubject().getId().equals(subjectId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResultDto createResult(ExamResultDto dto) {
        ExamResult result = new ExamResult();
        
        ExamSchedule schedule = scheduleRepository.findById(dto.getExamScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        result.setExamSchedule(schedule);
        
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        result.setStudent(student);
        
        result.setMarksObtained(dto.getMarksObtained());
        result.setGrade(dto.getGrade());
        result.setRemarks(dto.getRemarks());
        
        return mapToDto(resultRepository.save(result));
    }
    @Override
    public ExamResultDto updateResult(Long id, ExamResultDto dto) {
        ExamResult result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));
                
        result.setMarksObtained(dto.getMarksObtained());
        result.setGrade(dto.getGrade());
        result.setRemarks(dto.getRemarks());
        
        return mapToDto(resultRepository.save(result));
    }
    
    private ExamResultDto mapToDto(ExamResult result) {
        ExamResultDto dto = new ExamResultDto();
        dto.setId(result.getId());
        dto.setExamScheduleId(result.getExamSchedule().getId());
        if (result.getExamSchedule().getExam() != null) {
            dto.setExamId(result.getExamSchedule().getExam().getId());
            dto.setExamName(result.getExamSchedule().getExam().getName());
        }
        if (result.getExamSchedule().getSubject() != null) {
            dto.setSubjectId(result.getExamSchedule().getSubject().getId());
            dto.setSubjectName(result.getExamSchedule().getSubject().getName());
        }
        dto.setTotalMarks(result.getExamSchedule().getMaxMarks());
        dto.setStudentId(result.getStudent().getId());
        dto.setMarksObtained(result.getMarksObtained());
        dto.setGrade(result.getGrade());
        dto.setRemarks(result.getRemarks());
        return dto;
    }
}
