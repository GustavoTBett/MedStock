package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.client.Cliente;
import com.app.medStock.dto.client.ClienteInsert;
import com.app.medStock.model.Client;
import com.app.medStock.repository.ClientRepository;
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
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

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

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Client.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Client> clients = (List<Client>) clientRepository.findAll(predicate);
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
