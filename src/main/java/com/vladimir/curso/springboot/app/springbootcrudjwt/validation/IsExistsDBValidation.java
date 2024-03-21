package com.vladimir.curso.springboot.app.springbootcrudjwt.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.vladimir.curso.springboot.app.springbootcrudjwt.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsDBValidation implements ConstraintValidator<IsExistsDB, String>{

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !service.existsBySku(value);
    }

}
