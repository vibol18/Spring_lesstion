package com.example.real_school.transport.repository;

import com.example.real_school.transport.entity.TransportStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportStopRepository extends JpaRepository<TransportStop, Long> {
}
