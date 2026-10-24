package com.example.real_school.transport.service.impl;

import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import com.example.real_school.transport.dto.StudentTransportDto;
import com.example.real_school.transport.dto.TransportRouteDto;
import com.example.real_school.transport.dto.TransportStopDto;
import com.example.real_school.transport.entity.StudentTransport;
import com.example.real_school.transport.entity.TransportRoute;
import com.example.real_school.transport.entity.TransportStop;
import com.example.real_school.transport.repository.StudentTransportRepository;
import com.example.real_school.transport.repository.TransportRouteRepository;
import com.example.real_school.transport.repository.TransportStopRepository;
import com.example.real_school.transport.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {

    private final TransportRouteRepository routeRepository;
    private final TransportStopRepository stopRepository;
    private final StudentTransportRepository studentTransportRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<TransportRouteDto> getAllRoutes() {
        return routeRepository.findAll().stream().map(this::mapRouteToDto).collect(Collectors.toList());
    }

    @Override
    public TransportRouteDto getRouteById(Long id) {
        return mapRouteToDto(routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id)));
    }

    @Override
    public TransportRouteDto createRoute(TransportRouteDto dto) {
        TransportRoute route = new TransportRoute();
        route.setRouteName(dto.getRouteName());
        route.setStartLocation(dto.getStartLocation());
        route.setEndLocation(dto.getEndLocation());
        route.setVehicleNumber(dto.getVehicleNumber());
        route.setDriverName(dto.getDriverName());
        route.setDriverContact(dto.getDriverContact());
        return mapRouteToDto(routeRepository.save(route));
    }

    @Override
    public TransportRouteDto updateRoute(Long id, TransportRouteDto dto) {
        TransportRoute route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        route.setRouteName(dto.getRouteName());
        route.setStartLocation(dto.getStartLocation());
        route.setEndLocation(dto.getEndLocation());
        route.setVehicleNumber(dto.getVehicleNumber());
        route.setDriverName(dto.getDriverName());
        route.setDriverContact(dto.getDriverContact());
        return mapRouteToDto(routeRepository.save(route));
    }

    @Override
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) throw new RuntimeException("Route not found with id: " + id);
        routeRepository.deleteById(id);
    }

    @Override
    public List<TransportStopDto> getStopsByRoute(Long routeId) {
        return stopRepository.findAll().stream()
                .filter(s -> s.getTransportRoute() != null && s.getTransportRoute().getId().equals(routeId))
                .map(this::mapStopToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransportStopDto addStop(TransportStopDto dto) {
        TransportRoute route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + dto.getRouteId()));
        TransportStop stop = new TransportStop();
        stop.setTransportRoute(route);
        stop.setStopName(dto.getStopName());
        stop.setFare(dto.getFare());
        return mapStopToDto(stopRepository.save(stop));
    }

    @Override
    public StudentTransportDto assignStudentToRoute(StudentTransportDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));
        TransportRoute route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + dto.getRouteId()));
        TransportStop stop = stopRepository.findById(dto.getStopId())
                .orElseThrow(() -> new RuntimeException("Stop not found with id: " + dto.getStopId()));

        StudentTransport st = new StudentTransport();
        st.setStudent(student);
        st.setTransportRoute(route);
        st.setTransportStop(stop);
        return mapStudentTransportToDto(studentTransportRepository.save(st));
    }

    @Override
    public List<StudentTransportDto> getAllStudentTransport() {
        return studentTransportRepository.findAll().stream()
                .map(this::mapStudentTransportToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentTransportDto> getStudentTransport(Long studentId) {
        return studentTransportRepository.findAll().stream()
                .filter(st -> st.getStudent() != null && st.getStudent().getId().equals(studentId))
                .map(this::mapStudentTransportToDto)
                .collect(Collectors.toList());
    }

    private TransportRouteDto mapRouteToDto(TransportRoute r) {
        TransportRouteDto dto = new TransportRouteDto();
        dto.setId(r.getId());
        dto.setRouteName(r.getRouteName());
        dto.setStartLocation(r.getStartLocation());
        dto.setEndLocation(r.getEndLocation());
        dto.setVehicleNumber(r.getVehicleNumber());
        dto.setDriverName(r.getDriverName());
        dto.setDriverContact(r.getDriverContact());
        return dto;
    }

    private TransportStopDto mapStopToDto(TransportStop s) {
        TransportStopDto dto = new TransportStopDto();
        dto.setId(s.getId());
        dto.setRouteId(s.getTransportRoute() != null ? s.getTransportRoute().getId() : null);
        dto.setStopName(s.getStopName());
        dto.setFare(s.getFare());
        return dto;
    }

    private StudentTransportDto mapStudentTransportToDto(StudentTransport st) {
        StudentTransportDto dto = new StudentTransportDto();
        dto.setId(st.getId());
        dto.setStudentId(st.getStudent() != null ? st.getStudent().getId() : null);
        dto.setRouteId(st.getTransportRoute() != null ? st.getTransportRoute().getId() : null);
        dto.setStopId(st.getTransportStop() != null ? st.getTransportStop().getId() : null);
        return dto;
    }
}
