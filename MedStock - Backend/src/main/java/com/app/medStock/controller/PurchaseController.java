package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.purchase.Compra;
import com.app.medStock.dto.purchase.CompraInsert;
import com.app.medStock.model.Item;
import com.app.medStock.model.Provider;
import com.app.medStock.model.Purchase;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ProviderRepository;
import com.app.medStock.repository.PurchaseRepository;
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
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    public ResponseEntity create(@RequestBody CompraInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Provider provider = providerRepository.findById(entity.getFornecedorId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemRepository.findById(action).orElse(null));
                });
                Purchase purchase = new Purchase(entity.getDataCompra(), provider, itens);
                purchase = purchaseRepository.save(purchase);
                Compra compra = new Compra(purchase);
                return ResponseEntity.created(URI.create("api/purchase/" + compra.getId())).body(compra);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CompraInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Purchase purchase = purchaseRepository.findById(id).get();
                Provider provider = providerRepository.findById(entity.getFornecedorId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemRepository.findById(action).orElse(null));
                });
                purchase.setPurchaseDate(entity.getDataCompra());
                purchase.setProvider(provider);
                purchase.setItens(itens);
                Compra compra = new Compra(purchase);
                return ResponseEntity.ok(compra);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Purchase.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Purchase> purchases = (List<Purchase>) purchaseRepository.findAll(predicate);
                List<Compra> compras = new ArrayList<>();
                purchases.forEach(action -> {
                    compras.add(new Compra(action));
                });
                return ResponseEntity.ok(compras);
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
                List<Purchase> purchases = purchaseRepository.findAll();
                List<Compra> compras = new ArrayList<>();
                purchases.forEach(action -> {
                    compras.add(new Compra(action));
                });
                return ResponseEntity.ok(compras);
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
                Purchase purchase = purchaseRepository.findById(id).orElse(null);
                Compra compra = new Compra(purchase);
                return ResponseEntity.ok(compra);
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
                purchaseRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
