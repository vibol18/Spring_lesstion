package com.example.real_school.fee.dto;

import lombok.Data;

@Data
public class FeeCategoryDto {
    private Long id;
    private String name;
    private Double amount;
    private String description;
}
