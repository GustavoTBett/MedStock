package com.app.medStock.controller;

import com.app.medStock.model.Provider;
import com.app.medStock.repository.ProviderRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/provider")
public class ProviderController {
    
    @Autowired
    private ProviderRepository providerRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Provider entity) {
        Provider save = providerRepository.save(entity);
        return ResponseEntity.created(URI.create("api/provider/" + entity.getId())).body(save);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Provider> providers = providerRepository.findAll();
        return ResponseEntity.ok(providers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Provider providers = providerRepository.findById(id).orElse(null);
        return ResponseEntity.ok(providers);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        providerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
