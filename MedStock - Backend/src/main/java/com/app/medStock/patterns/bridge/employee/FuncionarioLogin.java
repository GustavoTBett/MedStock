package com.app.medStock.patterns.bridge.employee;

import com.app.medStock.patterns.bridge.LoginBridge;
import com.app.medStock.patterns.bridge.TipoLogin;

public class FuncionarioLogin extends LoginBridge {
    public FuncionarioLogin(TipoLogin tipoLogin) {
        super(tipoLogin);
    }

    @Override
    public void login() {
        System.out.println("Funcionario login");
    }
}
