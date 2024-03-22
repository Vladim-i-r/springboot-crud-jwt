package com.vladimir.curso.springboot.app.springbootcrudjwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Product;
import com.vladimir.curso.springboot.app.springbootcrudjwt.repositories.ProductRespository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRespository productRespository;   // DAO

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRespository.findAll();
    }

    //@SuppressWarnings("null")
    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    //@SuppressWarnings("null")
    @Transactional
    @Override
    public Product save(Product product) {
        return productRespository.save(product);
    }

    //@SuppressWarnings("null")
    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        
        Optional<Product> productOptional =  productRespository.findById(id);
        if(productOptional.isPresent()) {
            Product productDB = productOptional.orElseThrow();

            product.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setDescription(product.getDescription());
            productDB.setPrice(product.getPrice());
            return Optional.of(productRespository.save(productDB));
        }
        return productOptional;
    }

    //@SuppressWarnings("null")
    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional =  productRespository.findById(id);
        productOptional.ifPresent(productDB -> {
            productRespository.delete(productDB);
        });
        return productOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return productRespository.existsBySku(sku);
    }

    

}
