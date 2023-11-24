package com.app.medStock.controller;

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

    @PostMapping
    public ResponseEntity create(@RequestBody ClienteInsert entity) {
        try {
            Client client = new Client(entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getCpf(), entity.getCep(), entity.getEndereco(), entity.getEstado());
            client = clientRepository.save(client);
            Cliente cliente = new Cliente(client);
            return ResponseEntity.created(URI.create("api/client/" + cliente.getId())).body(cliente);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Client.class) Predicate predicate) {
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
    }

    @GetMapping
    public ResponseEntity findAll() {
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
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Client client = clientRepository.findById(id).orElse(null);
            Cliente cliente = new Cliente(client);
            return ResponseEntity.ok(cliente);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
}
