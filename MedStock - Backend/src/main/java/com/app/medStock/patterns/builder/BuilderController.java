package com.app.medStock.patterns.builder;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.purchase.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/builder")
public class BuilderController {

    @Autowired
    private RequestRateLimiter rateLimiter;

    @GetMapping
    public ResponseEntity builder() {
        if (rateLimiter.tryAcquire()) {
            Compra compra = new Compra();

            DirectorAction directorAction = new DirectorAction(compra);
            directorAction.createCompra(LocalDateTime.now());

            return ResponseEntity.ok(compra);
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
