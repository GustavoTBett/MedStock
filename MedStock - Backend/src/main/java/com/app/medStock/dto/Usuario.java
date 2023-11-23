package com.app.medStock.dto;

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
}
