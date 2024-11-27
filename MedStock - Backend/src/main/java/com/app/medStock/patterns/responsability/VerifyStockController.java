package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Item;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.repository.ItemRepository;
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
    private VerifyPurchaseOk verifyPurchaseOk;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping("/verify")
    public ResponseEntity verify(@RequestParam Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item ou lote não encontrado.");
        }

        String verify = null;
        try {
            verify = verifyPurchaseOk.verifyStock(item, item.getStock().getBatch());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!verify.equals("Valor correto")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar compra");
        } else {
            return ResponseEntity.ok("Compra correta");
        }
    }
}