package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.request.CategoryRequest;
import com.demo.web.dto.response.CategoryResponse;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final MappingHelper mappingHelper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(e -> modelMapper.map(e, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(e -> modelMapper.map(e, CategoryResponse.class))
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getName(), id.toString()));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryReq) {
        var category = categoryRepository.save(
                modelMapper.map(categoryReq, Category.class)
        );
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getName(), id.toString()));
        mappingHelper.mapIfSourceNotNullAndStringNotBlank(categoryRequest, category);
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
