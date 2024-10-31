package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.provider.Fornecedor;
import com.app.medStock.dto.provider.FornecedorInsert;
import com.app.medStock.model.Provider;
import com.app.medStock.repository.ProviderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
    @Operation(summary = "Criar fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar fornecedor", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Atualizar fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar fornecedor", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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

    @GetMapping
    @Operation(summary = "Listar todos os fornecedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de fornecedores",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Buscar fornecedor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))}),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Remover fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fornecedor removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
