package com.app.medStock.dto.user;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.RoleUsers;
import com.app.medStock.model.User;

/**
 *
 * @author gustavo
 */
public class Usuario extends MasterEntityDto{
    private String nome;
    private String email;
    private String senha;
    private RoleUsers role;
    
    public Usuario(User user) {
        this.setId(user.getId());
        this.setCriado(user.getCreated());
        this.setVersao(user.getVersion());
        this.nome = user.getName();
        this.email = user.getEmail();
        this.senha = user.getPassword();
        this.role = user.getRole();
    }

    public Usuario(String nome, String email, String senha, RoleUsers role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Usuario() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RoleUsers getRole() {
        return role;
    }

    public void setRole(RoleUsers role) {
        this.role = role;
    }
    
    
}
