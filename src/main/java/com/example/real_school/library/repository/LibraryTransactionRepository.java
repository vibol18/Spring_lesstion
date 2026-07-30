package com.example.real_school.library.repository;

import com.example.real_school.library.entity.LibraryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryTransactionRepository extends JpaRepository<LibraryTransaction, Long> {
}
