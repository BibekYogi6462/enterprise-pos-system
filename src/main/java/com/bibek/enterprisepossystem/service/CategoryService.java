package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto dto) throws Exception;
    void deleteCategory(Long id) throws Exception;


}
