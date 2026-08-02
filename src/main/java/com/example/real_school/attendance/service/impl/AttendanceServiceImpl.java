package com.example.real_school.attendance.service.impl;

import com.example.real_school.academic.entity.Section;
import com.example.real_school.academic.repository.SectionRepository;
import com.example.real_school.attendance.dto.AttendanceDto;
import com.example.real_school.attendance.dto.AttendanceSummaryDto;
import com.example.real_school.attendance.entity.Attendance;
import com.example.real_school.attendance.repository.AttendanceRepository;
import com.example.real_school.attendance.service.AttendanceService;
import com.example.real_school.common.enums.AttendanceStatus;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            SectionRepository sectionRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
    }
    @Override
    public AttendanceDto markAttendance(AttendanceDto dto) {
        Attendance attendance = new Attendance();

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        attendance.setStudent(student);

        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));
        attendance.setSection(section);

        attendance.setDate(dto.getAttendanceDate());
        attendance.setStatus(AttendanceStatus.valueOf(dto.getStatus()));
        attendance.setRemarks(dto.getRemark());

        return mapToDto(attendanceRepository.save(attendance));
    }

    @Override
    public List<AttendanceDto> getStudentAttendance(Long studentId) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getStudent() != null && a.getStudent().getId().equals(studentId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getSectionAttendanceByDate(Long sectionId, LocalDate date) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getSection() != null && a.getSection().getId().equals(sectionId)
                        && date.equals(a.getDate()))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public AttendanceDto updateAttendance(Long id, AttendanceDto dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setStatus(AttendanceStatus.valueOf(dto.getStatus()));
        attendance.setRemarks(dto.getRemark());

        return mapToDto(attendanceRepository.save(attendance));
    }
    @Override
    public AttendanceSummaryDto getStudentAttendanceSummary(Long studentId) {
        List<Attendance> attendances = attendanceRepository.findAll().stream()
                .filter(a -> a.getStudent() != null && a.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        AttendanceSummaryDto summary = new AttendanceSummaryDto();
        summary.setStudentId(studentId);
        summary.setTotalDays(attendances.size());
        summary.setPresentDays(
                (int) attendances.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count());
        summary.setAbsentDays((int) attendances.stream().filter(a -> a.getStatus() == AttendanceStatus.ABSENT).count());

        if (summary.getTotalDays() > 0) {
            summary.setPercentage((double) summary.getPresentDays() / summary.getTotalDays() * 100);
        } else {
            summary.setPercentage(0.0);
        }

        return summary;
    }

    private AttendanceDto mapToDto(Attendance attendance) {
        AttendanceDto dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setStudentId(attendance.getStudent().getId());
        dto.setSectionId(attendance.getSection().getId());
        dto.setAttendanceDate(attendance.getDate());
        dto.setStatus(attendance.getStatus().name());
        dto.setRemark(attendance.getRemarks());
        return dto;
    }
}
