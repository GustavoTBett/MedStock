package com.app.medStock.patterns.bridge.client;

import com.app.medStock.patterns.bridge.LoginAdm;
import com.app.medStock.patterns.bridge.LoginBridge;
import com.app.medStock.patterns.bridge.TipoLogin;

public class ClienteLoginAdm extends LoginBridge {
    public ClienteLoginAdm(TipoLogin tipoLogin) {
        super(tipoLogin);
    }

    @Override
    public void login() {
        System.out.println("Cliente login adm");
    }
}
