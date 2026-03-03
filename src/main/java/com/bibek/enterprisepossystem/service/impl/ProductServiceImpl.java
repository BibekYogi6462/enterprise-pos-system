package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.mapper.ProductMapper;
import com.bibek.enterprisepossystem.model.Category;
import com.bibek.enterprisepossystem.model.Product;
import com.bibek.enterprisepossystem.model.Store;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.ProductDto;
import com.bibek.enterprisepossystem.repository.CategoryRepository;
import com.bibek.enterprisepossystem.repository.ProductRepository;
import com.bibek.enterprisepossystem.repository.StoreRepository;
import com.bibek.enterprisepossystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;



    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                ()-> new Exception("Store not found")

        );
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()->new Exception("Category not found")
        );

        Product product = ProductMapper.toEntity(productDto, store, category);
        Product savedProduct = productRepository.save(product);
        return  ProductMapper.toDTO(savedProduct);

    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );


        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
       product.setImage(productDto.getImage());
       product.setMrp(productDto.getMrp());
       product.setSellingPrice(productDto.getSellingPrice());
       product.setBrand(product.getBrand());
       product.setUpdatedAt(LocalDateTime.now());

        if(productDto.getCategoryId()!=null){
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    () -> new Exception("Catgory Not Found")
            );
            product.setCategory(category);

        }



        Product savedProduct = productRepository.save(product);
       return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new Exception("product not found")
        );
        productRepository.delete(product);

    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products =  productRepository.findByStoreId(storeId);
                return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products =  productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
