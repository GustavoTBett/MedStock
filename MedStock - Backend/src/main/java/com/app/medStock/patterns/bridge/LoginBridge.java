package com.app.medStock.patterns.bridge;

import com.app.medStock.model.User;

public abstract class LoginBridge {
    protected User usuario;
    protected String senha;
    protected TipoLogin tipoLogin;

    public LoginBridge(TipoLogin tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    protected abstract String login();
}
