package com.vladimir.curso.springboot.app.springbootcrudjwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.Role;
import com.vladimir.curso.springboot.app.springbootcrudjwt.entities.User;
import com.vladimir.curso.springboot.app.springbootcrudjwt.repositories.RoleRepository;
import com.vladimir.curso.springboot.app.springbootcrudjwt.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository uRepository;

    @Autowired
    private RoleRepository rRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) uRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {

        Optional<Role> optionalRoleUser = rRepository.findByName("ROLE_USER");      // Esto es para asignarle el rol predeterminado de usuario
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(role-> roles.add(role));  // roles::add

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = rRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(role -> roles.add(role));
        }

        user.setRoles(roles);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return uRepository.save(user);
    }


}
