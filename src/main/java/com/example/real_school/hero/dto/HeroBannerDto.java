package com.example.real_school.hero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO as consumed by the frontend HeroSlider:
 * { id, imageUrl, headline, description, buttonLabel, buttonHref }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroBannerDto {

    private Long id;
    private String imageUrl;
    private String headline;
    private String description;
    private String buttonLabel;
    private String buttonHref;
    private Integer sortOrder;
    private Boolean active;
}
