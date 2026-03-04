package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class InventoryDto {

    private Long id;

    private BranchDto branch;

    private Long branchId;

    private Long productId;

    private ProductDto product;

    private Integer quantity;

    private LocalDateTime lastUpdated;
}
