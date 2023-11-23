package com.app.medStock.controller;

import com.app.medStock.model.Sale;
import com.app.medStock.model.Service;
import com.app.medStock.repository.SaleRepository;
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
@RequestMapping("/api/sale")
public class SaleController {
    
    @Autowired
    private SaleRepository saleRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Sale entity) {
        Sale save = saleRepository.save(entity);
        return ResponseEntity.created(URI.create("api/sale/" + entity.getId())).body(save);
    }
    
    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Sale.class) Predicate predicate) {
        List<Sale> sales = (List<Sale>) saleRepository.findAll(predicate);
        return ResponseEntity.ok(sales);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Sale> sales = saleRepository.findAll();
        return ResponseEntity.ok(sales);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Sale sale = saleRepository.findById(id).orElse(null);
        return ResponseEntity.ok(sale);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        saleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
