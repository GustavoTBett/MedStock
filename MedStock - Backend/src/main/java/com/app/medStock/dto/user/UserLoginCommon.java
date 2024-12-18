package com.app.medStock.dto.user;

import com.app.medStock.patterns.bridge.LoginBridge;
import com.app.medStock.patterns.bridge.TipoLogin;

public class UserLoginCommon extends LoginBridge {

    public UserLoginCommon(TipoLogin tipoLogin) {
        super(tipoLogin);
    }

    @Override
    public String login() {
        return this.tipoLogin.fazerLogin(this.usuario, this.senha);
    }
}
