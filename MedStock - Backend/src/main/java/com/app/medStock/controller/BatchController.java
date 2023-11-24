package com.app.medStock.controller;

import com.app.medStock.dto.batch.LoteInsert;
import com.app.medStock.dto.batch.Lote;
import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.QBatch;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.repository.ProductRepository;
import com.app.medStock.service.BatchService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
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
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody LoteInsert entity) {
        try {
            Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
            Batch batch = new Batch(entity.getNumero(), entity.getDataFabricacao(), entity.getDataValidade(), product);
            batch = batchRepository.save(batch);
            Lote loteInsert = new Lote(batch);
            return ResponseEntity.created(URI.create("api/batch/" + loteInsert.getId())).body(loteInsert);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Batch.class) Predicate predicate) {
        try {
            List<Batch> batch = (List<Batch>) batchRepository.findAll(predicate);
            List<Lote> lotes = new ArrayList<>();
            batch.forEach(action -> {
                lotes.add(new Lote(action));
            });
            return ResponseEntity.ok(lotes);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Batch> batchs = batchRepository.findAll();
        List<Lote> lotes = new ArrayList<>();
        batchs.forEach(action -> {
            lotes.add(new Lote(action));
        });
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Batch batch = batchService.findById(id);
        return ResponseEntity.ok(new Lote(batch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        batchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
