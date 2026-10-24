package com.example.real_school.hostel.repository;

import com.example.real_school.hostel.entity.HostelAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelAllocationRepository extends JpaRepository<HostelAllocation, Long> {
}
