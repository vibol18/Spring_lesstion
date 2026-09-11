package com.example.real_school.hero.service.impl;

import com.example.real_school.hero.dto.HeroBannerDto;
import com.example.real_school.hero.entity.HeroBanner;
import com.example.real_school.hero.repository.HeroBannerRepository;
import com.example.real_school.hero.service.HeroBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HeroBannerServiceImpl implements HeroBannerService {

    private final HeroBannerRepository heroBannerRepository;

    @Override
    public List<HeroBannerDto> getActiveBanners() {
        return heroBannerRepository.findByActiveTrueOrderBySortOrderAsc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroBannerDto> getAllBanners() {
        return heroBannerRepository.findAllByOrderBySortOrderAsc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HeroBannerDto createBanner(HeroBannerDto dto) {
        HeroBanner banner = HeroBanner.builder()
                .imageUrl(dto.getImageUrl())
                .headline(dto.getHeadline())
                .description(dto.getDescription())
                .buttonLabel(dto.getButtonLabel())
                .buttonHref(dto.getButtonHref())
                .sortOrder(dto.getSortOrder() == null ? 99 : dto.getSortOrder())
                .active(dto.getActive() == null || dto.getActive())
                .build();
        return mapToDto(heroBannerRepository.save(banner));
    }

    @Override
    public HeroBannerDto updateBanner(Long id, HeroBannerDto dto) {
        HeroBanner banner = heroBannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hero banner not found: " + id));
        banner.setImageUrl(dto.getImageUrl());
        banner.setHeadline(dto.getHeadline());
        banner.setDescription(dto.getDescription());
        banner.setButtonLabel(dto.getButtonLabel());
        banner.setButtonHref(dto.getButtonHref());
        if (dto.getSortOrder() != null) banner.setSortOrder(dto.getSortOrder());
        if (dto.getActive() != null) banner.setActive(dto.getActive());
        return mapToDto(heroBannerRepository.save(banner));
    }

    @Override
    public void deleteBanner(Long id) {
        heroBannerRepository.deleteById(id);
    }

    private HeroBannerDto mapToDto(HeroBanner b) {
        return HeroBannerDto.builder()
                .id(b.getId())
                .imageUrl(b.getImageUrl())
                .headline(b.getHeadline())
                .description(b.getDescription())
                .buttonLabel(b.getButtonLabel())
                .buttonHref(b.getButtonHref())
                .sortOrder(b.getSortOrder())
                .active(b.getActive())
                .build();
    }
}
