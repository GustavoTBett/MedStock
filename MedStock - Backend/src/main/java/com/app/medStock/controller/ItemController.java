package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.item.ItemDto;
import com.app.medStock.dto.item.ItemInsert;
import com.app.medStock.model.Item;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ProductRepository;
import com.app.medStock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @Autowired
    private StockRepository stockRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody ItemInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
                Stock stock = stockRepository.findById(entity.getEstoqueId()).orElse(null);
                Item item = new Item(product, entity.getQuantidade(), entity.getPreco(), entity.getJuros(), entity.getDesconto(), stock);
                item = itemRepository.save(item);
                ItemDto itemDto = new ItemDto(item);
                return ResponseEntity.created(URI.create("api/item/" + itemDto.getId())).body(itemDto);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ItemInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Item item = itemRepository.findById(id).get();
                Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
                item.setProduct(product);
                item.setQuantity(entity.getQuantidade());
                item.setPrice(entity.getPreco());
                item.setFees(entity.getJuros());
                item.setDiscount(entity.getDesconto());
                item = itemRepository.save(item);
                ItemDto itemDto = new ItemDto(item);
                return ResponseEntity.ok(itemDto);
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
                List<Item> itens = itemRepository.findAll();
                List<ItemDto> itensDto = new ArrayList<>();
                itens.forEach(action -> {
                    itensDto.add(new ItemDto(action));
                });
                return ResponseEntity.ok(itensDto);
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
                Item item = itemRepository.findById(id).orElse(null);
                ItemDto itemDto = new ItemDto(item);
                return ResponseEntity.ok(itemDto);
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
                itemRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
