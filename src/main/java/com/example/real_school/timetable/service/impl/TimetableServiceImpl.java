package com.example.real_school.timetable.service.impl;

import com.example.real_school.academic.entity.Section;
import com.example.real_school.academic.entity.Subject;
import com.example.real_school.academic.repository.SectionRepository;
import com.example.real_school.academic.repository.SubjectRepository;
import com.example.real_school.teacher.entity.Teacher;
import com.example.real_school.teacher.repository.TeacherRepository;
import com.example.real_school.timetable.dto.TimetableDto;
import com.example.real_school.timetable.entity.Timetable;
import com.example.real_school.timetable.repository.TimetableRepository;
import com.example.real_school.timetable.service.TimetableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public TimetableServiceImpl(TimetableRepository timetableRepository,
                                SectionRepository sectionRepository,
                                SubjectRepository subjectRepository,
                                TeacherRepository teacherRepository) {
        this.timetableRepository = timetableRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TimetableDto> getTimetableBySection(Long sectionId) {
        return timetableRepository.findAll().stream()
                .filter(t -> t.getSection() != null && t.getSection().getId().equals(sectionId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TimetableDto createTimetableEntry(TimetableDto dto) {
        Timetable timetable = new Timetable();
        
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));
        timetable.setSection(section);

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        timetable.setSubject(subject);

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        timetable.setTeacher(teacher);

        timetable.setDayOfWeek(dto.getDayOfWeek());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());
        timetable.setClassroom(dto.getRoomNumber());
        return mapToDto(timetableRepository.save(timetable));
    }
    @Override
    public TimetableDto updateTimetableEntry(Long id, TimetableDto dto) {
        Timetable timetable = timetableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timetable entry not found"));
        
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));
        timetable.setSection(section);

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        timetable.setSubject(subject);
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        timetable.setTeacher(teacher);

        timetable.setDayOfWeek(dto.getDayOfWeek());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());
        timetable.setClassroom(dto.getRoomNumber());

        return mapToDto(timetableRepository.save(timetable));
    }

    @Override
    public void deleteTimetableEntry(Long id) {
        if (!timetableRepository.existsById(id)) {
            throw new RuntimeException("Timetable entry not found");
        }
        timetableRepository.deleteById(id);
    }

    private TimetableDto mapToDto(Timetable timetable) {
        TimetableDto dto = new TimetableDto();
        dto.setId(timetable.getId());
        dto.setSectionId(timetable.getSection().getId());
        dto.setSubjectId(timetable.getSubject().getId());
        dto.setTeacherId(timetable.getTeacher().getId());
        dto.setDayOfWeek(timetable.getDayOfWeek());
        dto.setStartTime(timetable.getStartTime());
        dto.setEndTime(timetable.getEndTime());
        dto.setRoomNumber(timetable.getClassroom());
        return dto;
    }
}
