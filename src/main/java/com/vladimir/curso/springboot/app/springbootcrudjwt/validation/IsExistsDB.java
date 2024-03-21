package com.vladimir.curso.springboot.app.springbootcrudjwt.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsExistsDBValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDB {

   String message() default "(ya existe en la base de datos)";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
