package com.example.real_school.exam.repository;

import com.example.real_school.exam.entity.ReportCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {
}
