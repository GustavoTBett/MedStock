package com.app.medStock.patterns.bridge;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.user.UserLoginAdm;
import com.app.medStock.dto.user.UserLoginCommon;
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

            UserLoginCommon userLoginCommon = new UserLoginCommon(loginUsuario);
            userLoginCommon.setUsuario("teste");
            userLoginCommon.setSenha("123456");
            userLoginCommon.login();

            UserLoginAdm userLoginAdm = new UserLoginAdm(loginAdm);
            userLoginAdm.setUsuario("teste");
            userLoginAdm.setSenha("123456");
            userLoginAdm.login();

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
