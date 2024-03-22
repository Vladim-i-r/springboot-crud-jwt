package com.vladimir.curso.springboot.app.springbootcrudjwt;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Product;

@Component
public class ProductValidation implements Validator{

    //@SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);    // PARA VALIDAR DE FORMA PROGRAMAGTICA
    }

    //@SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "(Es requerido!)");
        /////ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "(se requiere una descripcion breve!)");

        if (product.getDescription()==null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "(se requiere una descripcion breve!)");
        }

        if (product.getPrice()==null) {
            errors.rejectValue("price", null, "El precio es requerido!");
        }else if (product.getPrice()<500) {
            errors.rejectValue("price", null, "Debe ser menor a 500!");
        }
    }

}
