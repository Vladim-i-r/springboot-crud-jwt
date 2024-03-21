package com.vladimir.curso.springboot.app.springbootcrudjwt.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return (value != null && !value.isBlank());                   //? Es un if pero simplificado
        return StringUtils.hasText(value);                    
    }
      
 
}
