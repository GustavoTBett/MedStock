package com.app.medStock.patterns.bridge.employee;

import com.app.medStock.patterns.bridge.LoginBridge;
import com.app.medStock.patterns.bridge.TipoLogin;

public class FuncionarioLoginAdm extends LoginBridge {
    public FuncionarioLoginAdm(TipoLogin tipoLogin) {
        super(tipoLogin);
    }

    @Override
    public void login() {
        System.out.println("Login funcionario adm");
    }
}
