package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Category;
import com.bibek.enterprisepossystem.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDTO(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();
    }
}
