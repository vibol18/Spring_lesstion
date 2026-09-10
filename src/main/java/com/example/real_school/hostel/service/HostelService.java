package com.example.real_school.hostel.service;

import com.example.real_school.hostel.dto.HostelAllocationDto;
import com.example.real_school.hostel.dto.HostelBlockDto;
import com.example.real_school.hostel.dto.HostelRoomDto;
import java.util.List;

public interface HostelService {
    // Blocks
    List<HostelBlockDto> getAllBlocks();
    HostelBlockDto createBlock(HostelBlockDto dto);

    // Rooms
    List<HostelRoomDto> getAllRooms();
    List<HostelRoomDto> getRoomsByBlock(Long blockId);
    HostelRoomDto createRoom(HostelRoomDto dto);

    // Allocations
    List<HostelAllocationDto> getAllocations();
    HostelAllocationDto allocateRoom(HostelAllocationDto dto);
    HostelAllocationDto vacateRoom(Long allocationId);
    List<HostelAllocationDto> getAllocationsByStudent(Long studentId);
}
