package com.example.real_school.hero.controller;

import com.example.real_school.hero.dto.HeroBannerDto;
import com.example.real_school.hero.service.HeroBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hero")
@RequiredArgsConstructor
@CrossOrigin
public class HeroBannerController {

    private final HeroBannerService heroBannerService;

    /** Public — the 3 active slides the homepage Swiper renders. */
    @GetMapping("/banners/active")
    public List<HeroBannerDto> getActiveBanners() {
        return heroBannerService.getActiveBanners();
    }

    /** Admin — full list including drafts. */
    @GetMapping("/banners")
    public List<HeroBannerDto> getAllBanners() {
        return heroBannerService.getAllBanners();
    }

    @PostMapping("/banners")
    @ResponseStatus(HttpStatus.CREATED)
    public HeroBannerDto createBanner(@RequestBody HeroBannerDto dto) {
        return heroBannerService.createBanner(dto);
    }

    @PutMapping("/banners/{id}")
    public HeroBannerDto updateBanner(@PathVariable Long id, @RequestBody HeroBannerDto dto) {
        return heroBannerService.updateBanner(id, dto);
    }

    @DeleteMapping("/banners/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanner(@PathVariable Long id) {
        heroBannerService.deleteBanner(id);
    }
}
