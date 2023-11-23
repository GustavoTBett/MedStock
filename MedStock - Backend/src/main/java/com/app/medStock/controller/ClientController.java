package com.app.medStock.controller;

import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.repository.ClientRepository;
import com.querydsl.core.types.Predicate;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
    public ResponseEntity create(@RequestBody Client entity) {
        Client save = clientRepository.save(entity);
        return ResponseEntity.created(URI.create("api/client/" + entity.getId())).body(save);
    }
    
    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Client.class) Predicate predicate) {
        List<Client> clients = (List<Client>) clientRepository.findAll(predicate);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return ResponseEntity.ok(client);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
