package com.app.medStock.patterns.bridge;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.user.UserLoginCommon;
import com.app.medStock.enums.RoleUsers;
import com.app.medStock.model.User;
import com.app.medStock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bridge")
public class BridgeController {

    @Autowired
    private RequestRateLimiter rateLimiter;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity bridge(String email, String password) {
        if (rateLimiter.tryAcquire()) {
            User user = userRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.badRequest().body("Usuário ou senha incorreto");
            }

            UserLoginCommon userCommon;

            if (user.getRole().equals(RoleUsers.USER)) {
                LoginUsuario loginUsuario = new LoginUsuario();
                userCommon =  new UserLoginCommon(loginUsuario);
                userCommon.setSenha(password);
                userCommon.setUsuario(user);
                return ResponseEntity.ok().body(userCommon.login());
            }

            if (user.getRole().equals(RoleUsers.ADMIN)) {
                LoginAdm loginAdm = new LoginAdm();
                userCommon =  new UserLoginCommon(loginAdm);
                userCommon.setSenha(password);
                userCommon.setUsuario(user);
                return ResponseEntity.ok().body(userCommon.login());
            }

            return ResponseEntity.badRequest().body("Role não encontrada");
        }

        return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
    }
}
