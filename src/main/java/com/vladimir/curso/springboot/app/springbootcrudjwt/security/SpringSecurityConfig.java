package com.vladimir.curso.springboot.app.springbootcrudjwt.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.vladimir.curso.springboot.app.springbootcrudjwt.security.filter.JwtAuthenticationFilter;
import com.vladimir.curso.springboot.app.springbootcrudjwt.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();      // sincornizacion con el filtro
    }

    @Bean                                               // Se crea un componente 
    PasswordEncoder passwordEncoder(){                  //* Esto proviene de spring security */
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{         //* Esto es que para que la pagina de users sea publica y se ppuedan crear/listar ya que spring security lo protege */
        return http.authorizeHttpRequests((authz)-> authz
        .requestMatchers(HttpMethod.GET,"/api/users").permitAll()
        .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
        ////.requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")
        ////.requestMatchers(HttpMethod.GET,"/api/products", "/api/products/{id}").hasAnyRole("ADMIN","USER")
        ////.requestMatchers(HttpMethod.POST,"/api/product").hasRole("ADMIN")
        ////.requestMatchers(HttpMethod.PUT,"/api/product/{id}").hasRole("ADMIN")
        ////.requestMatchers(HttpMethod.DELETE,"/api/product/{id}").hasRole("ADMIN")        //YA ESTA EN LAS ANOTACIONES
        .anyRequest().authenticated())
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))  //registrando el filtro 
        .addFilter(new JwtValidationFilter(authenticationManager()))  //registrando el filtro 
        .csrf(config -> config.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }  // se deja publico users y todo lo demas requiere autenticacion, csrf para evitar vulnerabilidades

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @SuppressWarnings("null")
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
