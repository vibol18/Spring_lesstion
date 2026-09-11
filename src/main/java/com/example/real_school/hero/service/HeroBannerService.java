package com.example.real_school.hero.service;

import com.example.real_school.hero.dto.HeroBannerDto;

import java.util.List;

public interface HeroBannerService {

    List<HeroBannerDto> getActiveBanners();

    List<HeroBannerDto> getAllBanners();

    HeroBannerDto createBanner(HeroBannerDto dto);

    HeroBannerDto updateBanner(Long id, HeroBannerDto dto);

    void deleteBanner(Long id);
}
