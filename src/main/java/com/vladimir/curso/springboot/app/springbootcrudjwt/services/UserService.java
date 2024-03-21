package com.vladimir.curso.springboot.app.springbootcrudjwt.services;

import java.util.List;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);
}
