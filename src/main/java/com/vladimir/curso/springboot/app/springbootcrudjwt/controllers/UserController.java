package com.vladimir.curso.springboot.app.springbootcrudjwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.User;
import com.vladimir.curso.springboot.app.springbootcrudjwt.services.UserService;

import jakarta.validation.Valid;

            //originPatterns = "*"      TODAS
@CrossOrigin(origins = "http://localhost:4200")   //! PERMITIR CONEXION CON FRONTEND
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService uService;

    @GetMapping
    public List<User> list(){
        return uService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")  //? OTRA MANERA DE ASIGNAR PERMISOS PERO CON ANOTACIONES, EN LUGAR DE PONERLO EN SPRINGSECURITYCONFIG
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){                 // cuando es validacion se ponde <?>
        if (result.hasFieldErrors()) {                                                                     //? PRIVADO SI NO AUTENTICA
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(uService.save(user));
    }

    @PostMapping("/register")                                                                              //? PUBLICA
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){                 
        user.setAdmin(false);
        return create(user, result);
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
