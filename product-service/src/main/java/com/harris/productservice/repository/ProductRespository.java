package com.harris.productservice.repository;

import com.harris.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends MongoRepository<Product, String> {
}
