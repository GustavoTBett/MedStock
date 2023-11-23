package com.app.medStock.controller;

import com.app.medStock.model.Item;
import com.app.medStock.model.Product;
import com.app.medStock.repository.ItemRepository;
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
@RequestMapping("/api/item")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Item entity) {
        Item save = itemRepository.save(entity);
        return ResponseEntity.created(URI.create("api/item/" + entity.getId())).body(save);
    }
    
    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Item.class) Predicate predicate) {
        List<Item> itens = (List<Item>) itemRepository.findAll(predicate);
        return ResponseEntity.ok(itens);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Item> itens = itemRepository.findAll();
        return ResponseEntity.ok(itens);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
