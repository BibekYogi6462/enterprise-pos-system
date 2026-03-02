package com.bibek.enterprisepossystem.controller;


import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.ProductDto;
import com.bibek.enterprisepossystem.payload.response.ApiResponse;
import com.bibek.enterprisepossystem.service.ProductService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

  @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto,
                                             @RequestHeader("Authorization") String jwt
                                             ) throws Exception {
      User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(
                productService.createProduct(
                        productDto, user
                )
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getByStoreId(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization") String jwt
                                             ) throws Exception {
        return ResponseEntity.ok(
                productService.getProductsByStoreId(
                        storeId
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,
                                                         @RequestBody ProductDto productDto,
                                                         @RequestHeader("Authorization") String jwt
                                             ) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(
                productService.updateProduct(
                        id,
                        productDto, user
                )
        );
    }


    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long storeId,
                                                         @RequestParam String keyword,
                                                         @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(
                productService.searchByKeyword(
                        storeId,
                        keyword
                )
        );
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(
                id, user
        );

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product deleted Successfully");

        return ResponseEntity.ok(
                apiResponse

        );
    }

}
