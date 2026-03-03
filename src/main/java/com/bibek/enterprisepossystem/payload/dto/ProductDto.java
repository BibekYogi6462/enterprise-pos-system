package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.model.Store;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {

    private Long id;

    private String name;

    private String sku;

    private String description;

    private Double mrp;

    private Double sellingPrice;
    private String brand;
    private String image;

    private CategoryDto category;

    private Long categoryId;

    @ManyToOne
    private Long storeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
