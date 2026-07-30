package com.example.real_school.transport.repository;

import com.example.real_school.transport.entity.StudentTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTransportRepository extends JpaRepository<StudentTransport, Long> {
}
