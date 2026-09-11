package com.example.real_school.hero.repository;

import com.example.real_school.hero.entity.HeroBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroBannerRepository extends JpaRepository<HeroBanner, Long> {

    List<HeroBanner> findByActiveTrueOrderBySortOrderAsc();

    List<HeroBanner> findAllByOrderBySortOrderAsc();
}
