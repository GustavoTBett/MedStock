package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.stock.Estoque;
import com.app.medStock.dto.stock.EstoqueInsert;
import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.repository.ProductRepository;
import com.app.medStock.repository.StockRepository;
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
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    public ResponseEntity create(@RequestBody EstoqueInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
                Batch batch = batchRepository.findById(entity.getLoteId()).orElse(null);
                Stock save = new Stock(product, batch, entity.getQuantidade());
                save = stockRepository.save(save);
                Estoque estoque = new Estoque(save);
                return ResponseEntity.created(URI.create("api/stock/" + estoque.getId())).body(estoque);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EstoqueInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
                Batch batch = batchRepository.findById(entity.getLoteId()).orElse(null);
                Stock stock = stockRepository.findById(id).get();
                stock.setProduct(product);
                stock.setBatch(batch);
                stock.setQuantity(entity.getQuantidade());
                Estoque estoque = new Estoque(stock);
                return ResponseEntity.ok(estoque);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Stock.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Stock> stock = (List<Stock>) stockRepository.findAll(predicate);
                List<Estoque> estoques = new ArrayList<>();
                stock.forEach(action -> {
                    estoques.add(new Estoque(action));
                });
                return ResponseEntity.ok(estoques);
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
                List<Stock> stock = (List<Stock>) stockRepository.findAll();
                List<Estoque> estoques = new ArrayList<>();
                stock.forEach(action -> {
                    estoques.add(new Estoque(action));
                });
                return ResponseEntity.ok(estoques);
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
                Stock stock = stockRepository.findById(id).orElse(null);
                Estoque estoque = new Estoque(stock);
                return ResponseEntity.ok(estoque);
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
                stockRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
