package com.app.medStock.patterns.bridge;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.patterns.bridge.client.ClienteLogin;
import com.app.medStock.patterns.bridge.client.ClienteLoginAdm;
import com.app.medStock.patterns.bridge.employee.FuncionarioLogin;
import com.app.medStock.patterns.bridge.employee.FuncionarioLoginAdm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bridge")
public class BridgeController {

    @Autowired
    private RequestRateLimiter rateLimiter;

    @GetMapping
    public ResponseEntity bridge() {
        if (rateLimiter.tryAcquire()) {
            LoginUsuario loginUsuario = new LoginUsuario();
            LoginAdm loginAdm = new LoginAdm();

            ClienteLogin clienteLogin = new ClienteLogin(loginUsuario);
            clienteLogin.setUsuario("teste");
            clienteLogin.setSenha("123456");
            clienteLogin.login();

            ClienteLoginAdm clienteLoginAdm = new ClienteLoginAdm(loginAdm);
            clienteLoginAdm.setUsuario("teste");
            clienteLoginAdm.setSenha("123456");
            clienteLoginAdm.login();

            FuncionarioLogin funcionarioLogin = new FuncionarioLogin(loginUsuario);
            funcionarioLogin.setUsuario("teste");
            funcionarioLogin.setSenha("123456");
            funcionarioLogin.login();

            FuncionarioLoginAdm funcionarioLoginAdm = new FuncionarioLoginAdm(loginAdm);
            funcionarioLoginAdm.setUsuario("teste");
            funcionarioLoginAdm.setSenha("123456");
            funcionarioLoginAdm.login();

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
