package com.vladimir.curso.springboot.app.springbootcrudjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean                                               // Se crea un componente 
    PasswordEncoder passwordEncoder(){                  //* Esto proviene de spring security */
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{         //* Esto es que para que la pagina de users sea publica y se ppuedan crear/listar ya que spring security lo protege */
        return http.authorizeHttpRequests((authz)-> authz
        .requestMatchers("/api/users").permitAll()
        .anyRequest().authenticated())
        .csrf(config -> config.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }  // se deja publico users y todo lo demas requiere autenticacion, csrf para evitar vulnerabilidades
}
