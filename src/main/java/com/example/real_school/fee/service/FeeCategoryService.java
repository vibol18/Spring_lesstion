package com.example.real_school.fee.service;

import com.example.real_school.fee.dto.FeeCategoryDto;
import java.util.List;

public interface FeeCategoryService {
    List<FeeCategoryDto> getAllCategories();
    FeeCategoryDto createCategory(FeeCategoryDto dto);
    FeeCategoryDto updateCategory(Long id, FeeCategoryDto dto);
}
