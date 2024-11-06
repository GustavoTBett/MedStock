package com.app.medStock.patterns.bridge;

import com.app.medStock.model.User;

public class LoginAdm implements TipoLogin{
    @Override
    public String fazerLogin(User user, String password) {
        if (user.getPassword().equals(password)) {
            return "Login Admin Sucesso";
        }
        return "Usuário ou senha incorreto";
    }
}