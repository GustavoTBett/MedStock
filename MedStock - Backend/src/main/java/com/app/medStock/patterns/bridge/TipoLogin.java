package com.app.medStock.patterns.bridge;

import com.app.medStock.model.User;

public interface TipoLogin {
    String fazerLogin(User usuario, String senha);
}
