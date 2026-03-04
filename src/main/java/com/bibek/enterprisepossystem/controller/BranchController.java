package com.bibek.enterprisepossystem.controller;

import com.bibek.enterprisepossystem.payload.dto.BranchDto;
import com.bibek.enterprisepossystem.payload.response.ApiResponse;
import com.bibek.enterprisepossystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

@PostMapping
    private ResponseEntity<BranchDto> createBranch(@RequestBody  BranchDto branchDto) throws Exception {
        BranchDto createdBranch = branchService.createBranch(branchDto);
        return ResponseEntity.ok(createdBranch);
    }

@PutMapping("/{id}")
     private ResponseEntity<BranchDto> updateBranch(@PathVariable Long id, @RequestBody  BranchDto branchDto) throws Exception {
        BranchDto updatedBranch = branchService.updateBranch(id, branchDto);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
     private ResponseEntity<ApiResponse> deleteBranchById(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted branch");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
     private ResponseEntity<BranchDto> getBranchById(@PathVariable  Long id) throws Exception {
        BranchDto branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/store/{storeId}")
     private ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(@PathVariable  Long storeId) throws Exception {
      List<BranchDto> branch = branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(branch);
    }



}
