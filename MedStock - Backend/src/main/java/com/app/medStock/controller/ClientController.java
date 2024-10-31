package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.client.Cliente;
import com.app.medStock.dto.client.ClienteInsert;
import com.app.medStock.model.Client;
import com.app.medStock.repository.ClientRepository;
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
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @Operation(summary = "Criar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar cliente", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @PostMapping
    public ResponseEntity create(@RequestBody ClienteInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = new Client(entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getCpf(), entity.getCep(), entity.getEndereco(), entity.getEstado());
                client = clientRepository.save(client);
                Cliente cliente = new Cliente(client);
                return ResponseEntity.created(URI.create("api/client/" + cliente.getId())).body(cliente);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Atualizar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar cliente", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ClienteInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(id).get();
                client.setName(entity.getNome());
                client.setEmail(entity.getEmail());
                client.setPhone(entity.getTelefone());
                client.setCpf(entity.getCpf());
                client.setZipcode(entity.getCep());
                client.setAddress(entity.getEndereco());
                client.setState(entity.getEstado());
                client = clientRepository.save(client);
                Cliente cliente = new Cliente(client);
                return ResponseEntity.ok(cliente);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @GetMapping
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Client> clients = clientRepository.findAll();
                List<Cliente> clientes = new ArrayList<>();
                clients.forEach(action -> {
                    clientes.add(new Cliente(action));
                });
                return ResponseEntity.ok(clientes);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }

    }

    @Operation(summary = "Buscar cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(id).orElse(null);
                Cliente cliente = new Cliente(client);
                return ResponseEntity.ok(cliente);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Remover cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                clientRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
