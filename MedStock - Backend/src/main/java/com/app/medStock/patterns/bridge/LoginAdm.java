package com.app.medStock.patterns.bridge;

public class LoginAdm implements TipoLogin{
    @Override
    public void fazerLogin(String usuario, String senha) {
        System.out.println("Login tipo adm");
        this.fazerLogin(usuario, senha);
    }
}