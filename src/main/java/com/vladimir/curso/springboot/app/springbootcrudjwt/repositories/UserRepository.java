package com.vladimir.curso.springboot.app.springbootcrudjwt.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
    
    Optional<User> findByUsername(String username);
} 