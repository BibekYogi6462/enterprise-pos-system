package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.mapper.InventoryMapper;
import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Inventory;
import com.bibek.enterprisepossystem.model.Product;
import com.bibek.enterprisepossystem.payload.dto.InventoryDto;
import com.bibek.enterprisepossystem.repository.BranchRepository;
import com.bibek.enterprisepossystem.repository.InventoryRepository;
import com.bibek.enterprisepossystem.repository.ProductRepository;
import com.bibek.enterprisepossystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                ()-> new Exception("branch not exist")
        );
        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()-> new Exception("product not exist")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDto, branch, product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist")
        );
        inventory.setQuantity(inventoryDto.getQuantity());

        Inventory updatedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist")
        );
        inventoryRepository.delete(inventory);

    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist")
        );
        return  InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDTO(inventory);

    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventory = inventoryRepository.findByBranchId(branchId);
        return inventory.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }


}
