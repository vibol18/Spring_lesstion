package com.example.real_school.fee.repository;

import com.example.real_school.fee.entity.FeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeCategoryRepository extends JpaRepository<FeeCategory, Long> {
}
