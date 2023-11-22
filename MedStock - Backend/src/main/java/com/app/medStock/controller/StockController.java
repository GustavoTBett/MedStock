package com.app.medStock.controller;

import com.app.medStock.model.Stock;
import com.app.medStock.repository.StockRepository;
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
@RequestMapping("/api/stock")
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Stock entity) {
        Stock save = stockRepository.save(entity);
        return ResponseEntity.created(URI.create("api/stock/" + entity.getId())).body(save);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Stock> stock = stockRepository.findAll();
        return ResponseEntity.ok(stock);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        return ResponseEntity.ok(stock);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        stockRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
