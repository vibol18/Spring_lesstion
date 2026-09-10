package com.example.real_school.twofactor.repository;

import com.example.real_school.twofactor.entity.OtpCode;
import com.example.real_school.twofactor.entity.OtpPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

    Optional<OtpCode> findTopByUserIdAndPurposeAndUsedFalseOrderByCreatedAtDesc(Long userId, OtpPurpose purpose);

    void deleteByUserIdAndPurpose(Long userId, OtpPurpose purpose);
}