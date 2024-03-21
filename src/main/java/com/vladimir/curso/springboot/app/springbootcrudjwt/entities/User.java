package com.vladimir.curso.springboot.app.springbootcrudjwt.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany                                                         //? UNIDIRECCIONAL, ESTA APP SOLO REQUIERE SABER LOS ROLES DE CADA USUARIO Y NO OBTENER LOS USUARIOS POR ROLES
    @JoinTable(
        name="users_roles",                                             //? SE MAPEA LA TABLA INTERMEDIA
        joinColumns = @JoinColumn(name="user_id"),                      //? FOREIGN KEY
        inverseJoinColumns = @JoinColumn(name="role_id"),               //? FOREIGN KEY
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
        
    )
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    
}
