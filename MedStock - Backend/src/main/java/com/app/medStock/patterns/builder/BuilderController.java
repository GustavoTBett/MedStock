package com.app.medStock.patterns.builder;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.model.Service;
import com.app.medStock.patterns.builder.purchase.Compra;
import com.app.medStock.patterns.builder.sale.Venda;
import com.app.medStock.patterns.builder.service.Servico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
