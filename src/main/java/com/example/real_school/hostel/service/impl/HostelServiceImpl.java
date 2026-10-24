package com.example.real_school.hostel.service.impl;

import com.example.real_school.hostel.dto.HostelAllocationDto;
import com.example.real_school.hostel.dto.HostelBlockDto;
import com.example.real_school.hostel.dto.HostelRoomDto;
import com.example.real_school.hostel.entity.HostelAllocation;
import com.example.real_school.hostel.entity.HostelBlock;
import com.example.real_school.hostel.entity.HostelRoom;
import com.example.real_school.hostel.repository.HostelAllocationRepository;
import com.example.real_school.hostel.repository.HostelBlockRepository;
import com.example.real_school.hostel.repository.HostelRoomRepository;
import com.example.real_school.hostel.service.HostelService;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HostelServiceImpl implements HostelService {

    private final HostelBlockRepository blockRepository;
    private final HostelRoomRepository roomRepository;
    private final HostelAllocationRepository allocationRepository;
    private final StudentRepository studentRepository;

    // --- Blocks ---
    @Override
    public List<HostelBlockDto> getAllBlocks() {
        return blockRepository.findAll().stream().map(this::mapBlockToDto).collect(Collectors.toList());
    }

    @Override
    public HostelBlockDto createBlock(HostelBlockDto dto) {
        HostelBlock block = new HostelBlock();
        block.setName(dto.getName());
        block.setType(dto.getType());
        block.setDescription(dto.getDescription());
        return mapBlockToDto(blockRepository.save(block));
    }

    // --- Rooms ---
    @Override
    public List<HostelRoomDto> getAllRooms() {
        return roomRepository.findAll().stream().map(this::mapRoomToDto).collect(Collectors.toList());
    }

    @Override
    public List<HostelRoomDto> getRoomsByBlock(Long blockId) {
        return roomRepository.findAll().stream()
                .filter(r -> r.getHostelBlock() != null && r.getHostelBlock().getId().equals(blockId))
                .map(this::mapRoomToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HostelRoomDto createRoom(HostelRoomDto dto) {
        HostelBlock block = blockRepository.findById(dto.getBlockId())
                .orElseThrow(() -> new RuntimeException("Block not found with id: " + dto.getBlockId()));
        HostelRoom room = new HostelRoom();
        room.setHostelBlock(block);
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setCapacity(dto.getCapacity());
        room.setOccupied(0);
        room.setCostPerMonth(dto.getCostPerMonth());
        return mapRoomToDto(roomRepository.save(room));
    }

    // --- Allocations ---
    @Override
    public List<HostelAllocationDto> getAllocations() {
        return allocationRepository.findAll().stream()
                .map(this::mapAllocationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HostelAllocationDto allocateRoom(HostelAllocationDto dto) {
        HostelRoom room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + dto.getRoomId()));
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));

        HostelAllocation allocation = new HostelAllocation();
        allocation.setHostelRoom(room);
        allocation.setStudent(student);
        allocation.setAllocationDate(dto.getAllocationDate() != null ? dto.getAllocationDate() : LocalDate.now());
        allocation.setIsActive(true);

        // Increment occupied count
        room.setOccupied(room.getOccupied() == null ? 1 : room.getOccupied() + 1);
        roomRepository.save(room);

        return mapAllocationToDto(allocationRepository.save(allocation));
    }

    @Override
    public HostelAllocationDto vacateRoom(Long allocationId) {
        HostelAllocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + allocationId));
        allocation.setIsActive(false);
        allocation.setVacantDate(LocalDate.now());

        HostelRoom room = allocation.getHostelRoom();
        if (room.getOccupied() != null && room.getOccupied() > 0) {
            room.setOccupied(room.getOccupied() - 1);
            roomRepository.save(room);
        }
        return mapAllocationToDto(allocationRepository.save(allocation));
    }

    @Override
    public List<HostelAllocationDto> getAllocationsByStudent(Long studentId) {
        return allocationRepository.findAll().stream()
                .filter(a -> a.getStudent() != null && a.getStudent().getId().equals(studentId))
                .map(this::mapAllocationToDto)
                .collect(Collectors.toList());
    }

    // --- Mappers ---
    private HostelBlockDto mapBlockToDto(HostelBlock b) {
        HostelBlockDto dto = new HostelBlockDto();
        dto.setId(b.getId());
        dto.setName(b.getName());
        dto.setType(b.getType());
        dto.setDescription(b.getDescription());
        return dto;
    }

    private HostelRoomDto mapRoomToDto(HostelRoom r) {
        HostelRoomDto dto = new HostelRoomDto();
        dto.setId(r.getId());
        dto.setBlockId(r.getHostelBlock() != null ? r.getHostelBlock().getId() : null);
        dto.setRoomNumber(r.getRoomNumber());
        dto.setRoomType(r.getRoomType());
        dto.setCapacity(r.getCapacity());
        dto.setOccupied(r.getOccupied());
        dto.setCostPerMonth(r.getCostPerMonth());
        return dto;
    }

    private HostelAllocationDto mapAllocationToDto(HostelAllocation a) {
        HostelAllocationDto dto = new HostelAllocationDto();
        dto.setId(a.getId());
        dto.setRoomId(a.getHostelRoom() != null ? a.getHostelRoom().getId() : null);
        dto.setStudentId(a.getStudent() != null ? a.getStudent().getId() : null);
        dto.setAllocationDate(a.getAllocationDate());
        dto.setVacantDate(a.getVacantDate());
        dto.setIsActive(a.getIsActive());
        return dto;
    }
}
