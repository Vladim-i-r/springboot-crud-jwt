package com.vladimir.curso.springboot.app.springbootcrudjwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Product;
import com.vladimir.curso.springboot.app.springbootcrudjwt.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // @Autowired
    // private ProductValidation validation;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){   //? El requestbody es la estructura json que recibira
       // validation.validate(product, result);  // Esto es otra manera de validar, mediante otra clase ProductValidation y no messages.propeties
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Product productNew = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,  @PathVariable Long id){  //! El binding debe estar al lado del objeto a validar, product en este caso
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        Optional<Product> prdOptional = service.update(id, product);
        if (prdOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(prdOptional.orElseThrow());
        } 
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        
        Optional<Product> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
        ////return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        ////return ResponseEntity.status(400).body(errors);
    }
}
