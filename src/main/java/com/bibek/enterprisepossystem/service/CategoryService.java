package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto dto);
    void deleteCategory(Long id);


}
