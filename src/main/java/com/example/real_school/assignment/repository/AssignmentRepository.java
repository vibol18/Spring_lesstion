package com.example.real_school.assignment.repository;

import com.example.real_school.assignment.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    // Query directly in DB instead of pulling all rows into memory
    List<Assignment> findBySectionId(Long sectionId);
}