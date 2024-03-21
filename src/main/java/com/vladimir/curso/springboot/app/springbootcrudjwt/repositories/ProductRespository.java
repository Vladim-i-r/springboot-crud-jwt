package com.vladimir.curso.springboot.app.springbootcrudjwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Product;

public interface ProductRespository extends CrudRepository<Product, Long>{
    
    boolean existsBySku(String sku);
}
