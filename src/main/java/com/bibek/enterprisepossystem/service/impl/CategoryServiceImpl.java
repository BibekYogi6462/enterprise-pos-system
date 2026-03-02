package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.CategoryDto;
import com.bibek.enterprisepossystem.repository.CategoryRepository;
import com.bibek.enterprisepossystem.service.CategoryService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        User user
        return null;
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        return List.of();
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
