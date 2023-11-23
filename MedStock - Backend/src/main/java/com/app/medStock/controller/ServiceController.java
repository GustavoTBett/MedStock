package com.app.medStock.controller;

import com.app.medStock.model.Service;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.ServiceRepository;
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
@RequestMapping("/api/service")
public class ServiceController {
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Service entity) {
        Service save = serviceRepository.save(entity);
        return ResponseEntity.created(URI.create("api/service/" + entity.getId())).body(save);
    }
    
    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Service.class) Predicate predicate) {
        List<Service> service = (List<Service>) serviceRepository.findAll(predicate);
        return ResponseEntity.ok(service);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Service> service = serviceRepository.findAll();
        return ResponseEntity.ok(service);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Service service = serviceRepository.findById(id).orElse(null);
        return ResponseEntity.ok(service);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        serviceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
