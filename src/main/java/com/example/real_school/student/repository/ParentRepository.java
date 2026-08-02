package com.example.real_school.student.repository;

import com.example.real_school.student.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p FROM Parent p JOIN p.children c WHERE c.id = :studentId")
    List<Parent> findAllByChildrenId(@Param("studentId") Long studentId);
}