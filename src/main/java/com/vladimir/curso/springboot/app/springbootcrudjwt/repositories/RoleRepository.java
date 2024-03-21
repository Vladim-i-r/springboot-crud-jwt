package com.vladimir.curso.springboot.app.springbootcrudjwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
