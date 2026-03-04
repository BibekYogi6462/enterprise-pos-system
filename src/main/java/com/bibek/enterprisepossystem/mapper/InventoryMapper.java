package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Inventory;
import com.bibek.enterprisepossystem.model.Product;
import com.bibek.enterprisepossystem.payload.dto.InventoryDto;

public class InventoryMapper {

    public static InventoryDto toDTO(Inventory inventory){
           return InventoryDto.builder()
                   .id(inventory.getId())
                   .branchId(inventory.getBranch().getId())
                   .productId(inventory.getProduct().getId())
                   .product(ProductMapper.toDTO(inventory.getProduct()))
                   .quantity(inventory.getQuantity())
                   .build();


    }
    public static Inventory toEntity(InventoryDto inventoryDto, Branch branch
    , Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
