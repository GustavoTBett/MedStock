package com.app.medStock.patterns.bridge;

public abstract class LoginBridge {
    protected String usuario;
    protected String senha;
    protected TipoLogin tipoLogin;

    public LoginBridge(TipoLogin tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    protected abstract void login();
}
