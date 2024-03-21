package com.vladimir.curso.springboot.app.springbootcrudjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig {

    @Bean                                               // Se crea un componente 
    PasswordEncoder passwordEncoder(){                  //* Esto proviene de spring security */
        return new BCryptPasswordEncoder();
    }
}
