package com.vladimir.curso.springboot.app.springbootcrudjwt.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);
}
