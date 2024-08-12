package com.product.repository;

import com.product.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {

    Product findByName(String name);
}
