package com.app.medStock.controller;

import com.app.medStock.model.Purchase;
import com.app.medStock.repository.PurchaseRepository;
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
@RequestMapping("/api/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Purchase entity) {
        Purchase save = purchaseRepository.save(entity);
        return ResponseEntity.created(URI.create("api/purchase/" + entity.getId())).body(save);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return ResponseEntity.ok(purchases);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        return ResponseEntity.ok(purchase);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        purchaseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
