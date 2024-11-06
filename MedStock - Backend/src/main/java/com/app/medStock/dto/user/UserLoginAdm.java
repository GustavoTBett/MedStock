package com.app.medStock.dto.user;

import com.app.medStock.patterns.bridge.LoginBridge;
import com.app.medStock.patterns.bridge.TipoLogin;

public class UserLoginAdm extends LoginBridge {
    public UserLoginAdm(TipoLogin tipoLogin) {
        super(tipoLogin);
    }

    @Override
    public void login() {
        System.out.println("Cliente login adm");
    }
}
