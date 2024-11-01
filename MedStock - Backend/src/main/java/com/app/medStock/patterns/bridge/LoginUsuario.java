package com.app.medStock.patterns.bridge;

public class LoginUsuario implements TipoLogin{
    @Override
    public void fazerLogin(String usuario, String senha) {
        System.out.println("Login tipo usuario");
        this.fazerLogin(usuario, senha);
    }
}
