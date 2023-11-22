package com.app.medStock.controller;

import com.app.medStock.model.Batch;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.service.BatchService;
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
@RequestMapping("/api/batch")
public class BatchController {
    
    @Autowired
    private BatchService batchService;
    
    @Autowired
    private BatchRepository batchRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Batch entity) {
        Batch save = batchRepository.save(entity);
        return ResponseEntity.created(URI.create("api/batch/" + entity.getId())).body(save);
    }
    
    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Batch.class) Predicate predicate) {
        List<Batch> batch = (List<Batch>) batchRepository.findAll(predicate);
        return ResponseEntity.ok(batch);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Batch> batchs = batchRepository.findAll();
        return ResponseEntity.ok(batchs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Batch batch = batchService.findById(id);
        return ResponseEntity.ok(batch);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        batchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
