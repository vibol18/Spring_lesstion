package com.example.real_school.exam.service.impl;

import com.example.real_school.academic.entity.SchoolClass;
import com.example.real_school.academic.entity.Subject;
import com.example.real_school.academic.repository.SchoolClassRepository;
import com.example.real_school.academic.repository.SubjectRepository;
import com.example.real_school.exam.dto.ExamScheduleDto;
import com.example.real_school.exam.entity.Exam;
import com.example.real_school.exam.entity.ExamSchedule;
import com.example.real_school.exam.repository.ExamRepository;
import com.example.real_school.exam.repository.ExamScheduleRepository;
import com.example.real_school.exam.service.ExamScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamScheduleServiceImpl implements ExamScheduleService {

    private final ExamScheduleRepository scheduleRepository;
    private final ExamRepository examRepository;
    private final SchoolClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public ExamScheduleServiceImpl(ExamScheduleRepository scheduleRepository,
                                   ExamRepository examRepository,
                                   SchoolClassRepository classRepository,
                                   SubjectRepository subjectRepository) {
        this.scheduleRepository = scheduleRepository;
        this.examRepository = examRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ExamScheduleDto> getSchedulesByExam(Long examId) {
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getExam() != null && s.getExam().getId().equals(examId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamScheduleDto createSchedule(ExamScheduleDto dto) {
        ExamSchedule schedule = new ExamSchedule();
        
        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        schedule.setExam(exam);
        
        SchoolClass schoolClass = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        schedule.setSchoolClass(schoolClass);
        
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        schedule.setSubject(subject);
        
        schedule.setDate(dto.getDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setMaxMarks(dto.getMaxMarks());
        schedule.setPassMarks(dto.getPassMarks());
        
        return mapToDto(scheduleRepository.save(schedule));
    }

    @Override
    public ExamScheduleDto updateSchedule(Long id, ExamScheduleDto dto) {
        ExamSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        
        // Assuming exam/class/subject can be updated
        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        schedule.setExam(exam);
        
        SchoolClass schoolClass = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        schedule.setSchoolClass(schoolClass);
        
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        schedule.setSubject(subject);
        
        schedule.setDate(dto.getDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setMaxMarks(dto.getMaxMarks());
        schedule.setPassMarks(dto.getPassMarks());
        
        return mapToDto(scheduleRepository.save(schedule));
    }
    
    private ExamScheduleDto mapToDto(ExamSchedule schedule) {
        ExamScheduleDto dto = new ExamScheduleDto();
        dto.setId(schedule.getId());
        dto.setExamId(schedule.getExam().getId());
        dto.setClassId(schedule.getSchoolClass().getId());
        dto.setSubjectId(schedule.getSubject().getId());
        dto.setDate(schedule.getDate());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setMaxMarks(schedule.getMaxMarks());
        dto.setPassMarks(schedule.getPassMarks());
        return dto;
    }
}
