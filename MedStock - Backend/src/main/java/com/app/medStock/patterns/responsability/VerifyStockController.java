package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class VerifyStockController {

    @Autowired
    private VerifyStockOk verifyStockOk;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping("/verify")
    public ResponseEntity verifyStock(@RequestParam Long productId, @RequestParam Long batchId) {
        Product product = productRepository.findById(productId).orElse(null);
        Batch batch = batchRepository.findById(batchId).orElse(null);

        if (product == null || batch == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto ou lote não encontrado.");
        }

        long quantity = 0;
        try {
            quantity = verifyStockOk.verifyStock(product, batch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Quantidade disponível: " + quantity);
    }
}