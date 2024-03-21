package com.vladimir.curso.springboot.app.springbootcrudjwt.services;

import java.util.List;
import java.util.Optional;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Product;

public interface ProductService {
    
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);

    boolean existsBySku(String sku);

}
