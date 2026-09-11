package com.example.real_school.hero.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hero_banners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Position in the 3-slide carousel: 1, 2, or 3. */
    @Column(nullable = false)
    private Integer sortOrder;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String headline;

    @Column(length = 1500)
    private String description;

    @Column(nullable = false)
    private String buttonLabel;

    @Column(nullable = false)
    private String buttonHref;

    @Column(nullable = false)
    private Boolean active = true;
}
