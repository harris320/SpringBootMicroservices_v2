package com.harris.productservice.service;

import com.harris.productservice.dto.ProductRequest;
import com.harris.productservice.dto.ProductResponse;
import com.harris.productservice.model.Product;
import com.harris.productservice.repository.ProductRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRespository productRespository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRespository.save(product);
        log.info("Product is saved with id {} ", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRespository.findAll();

        return productList.stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return productResponse;
    }
}
