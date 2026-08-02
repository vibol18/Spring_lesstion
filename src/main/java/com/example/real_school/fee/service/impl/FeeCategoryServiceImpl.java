package com.example.real_school.fee.service.impl;

import com.example.real_school.fee.dto.FeeCategoryDto;
import com.example.real_school.fee.entity.FeeCategory;
import com.example.real_school.fee.repository.FeeCategoryRepository;
import com.example.real_school.fee.service.FeeCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeeCategoryServiceImpl implements FeeCategoryService {

    private final FeeCategoryRepository categoryRepository;

    public FeeCategoryServiceImpl(FeeCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<FeeCategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeeCategoryDto createCategory(FeeCategoryDto dto) {
        FeeCategory category = new FeeCategory();
        category.setName(dto.getName());
        category.setAmount(dto.getAmount());
        category.setDescription(dto.getDescription());
        
        return mapToDto(categoryRepository.save(category));
    }

    @Override
    public FeeCategoryDto updateCategory(Long id, FeeCategoryDto dto) {
        FeeCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
                
        category.setName(dto.getName());
        category.setAmount(dto.getAmount());
        category.setDescription(dto.getDescription());
        
        return mapToDto(categoryRepository.save(category));
    }
    
    private FeeCategoryDto mapToDto(FeeCategory category) {
        FeeCategoryDto dto = new FeeCategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAmount(category.getAmount());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
