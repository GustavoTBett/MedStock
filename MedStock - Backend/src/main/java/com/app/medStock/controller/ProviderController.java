package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.provider.Fornecedor;
import com.app.medStock.dto.provider.FornecedorInsert;
import com.app.medStock.model.Provider;
import com.app.medStock.repository.ProviderRepository;
import com.querydsl.core.types.Predicate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    public ResponseEntity create(@RequestBody FornecedorInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Provider provider = new Provider(entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getCnpj(), entity.getCep(), entity.getEndereco(), entity.getEstado());
                provider = providerRepository.save(provider);
                Fornecedor fornecedor = new Fornecedor(provider);
                return ResponseEntity.created(URI.create("api/provider/" + fornecedor.getId())).body(fornecedor);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FornecedorInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Provider provider = providerRepository.findById(id).get();
                provider.setName(entity.getNome());
                provider.setEmail(entity.getEmail());
                provider.setPhone(entity.getTelefone());
                provider.setCnpj(entity.getCnpj());
                provider.setZipcode(entity.getCep());
                provider.setAddress(entity.getEndereco());
                provider.setState(entity.getEstado());
                Fornecedor fornecedor = new Fornecedor(provider);
                return ResponseEntity.ok(fornecedor);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Provider.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Provider> providers = (List<Provider>) providerRepository.findAll(predicate);
                List<Fornecedor> fornecedores = new ArrayList<>();
                providers.forEach(action -> {
                    fornecedores.add(new Fornecedor(action));
                });
                return ResponseEntity.ok(fornecedores);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Provider> providers = providerRepository.findAll();
                List<Fornecedor> fornecedores = new ArrayList<>();
                providers.forEach(action -> {
                    fornecedores.add(new Fornecedor(action));
                });
                return ResponseEntity.ok(fornecedores);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Provider providers = providerRepository.findById(id).orElse(null);
                Fornecedor fornecedor = new Fornecedor(providers);
                return ResponseEntity.ok(fornecedor);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                providerRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
