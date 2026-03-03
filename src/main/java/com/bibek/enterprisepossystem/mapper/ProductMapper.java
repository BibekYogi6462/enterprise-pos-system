package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Category;
import com.bibek.enterprisepossystem.model.Product;
import com.bibek.enterprisepossystem.model.Store;
import com.bibek.enterprisepossystem.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDTO(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
//                .categoryId(pr)

    }

    public static Product toEntity(ProductDto productDto, Store store, Category category){
         return Product.builder()
                 .name(productDto.getName())
                 .store(store)
                 .sku(productDto.getSku())
                 .description(productDto.getDescription())
                 .mrp(productDto.getMrp())
                 .sellingPrice(productDto.getSellingPrice())
                 .brand(productDto.getBrand())
                 .build();
    }


}
