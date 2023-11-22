package com.app.medStock.model;

import com.app.medStock.enums.RoleUsers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "app_user")
public class User extends MasterEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private RoleUsers role;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUsers getRole() {
        return role;
    }

    public void setRole(RoleUsers role) {
        this.role = role;
    }

    public User() {
    }

    public User(String name, String email, String password, RoleUsers role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
