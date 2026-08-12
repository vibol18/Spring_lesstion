package com.example.real_school.transport.service;

import com.example.real_school.transport.dto.StudentTransportDto;
import com.example.real_school.transport.dto.TransportRouteDto;
import com.example.real_school.transport.dto.TransportStopDto;
import java.util.List;

public interface TransportService {
    List<TransportRouteDto> getAllRoutes();
    TransportRouteDto getRouteById(Long id);
    TransportRouteDto createRoute(TransportRouteDto dto);
    TransportRouteDto updateRoute(Long id, TransportRouteDto dto);
    void deleteRoute(Long id);

    List<TransportStopDto> getStopsByRoute(Long routeId);
    TransportStopDto addStop(TransportStopDto dto);

    StudentTransportDto assignStudentToRoute(StudentTransportDto dto);
    List<StudentTransportDto> getStudentTransport(Long studentId);
}
