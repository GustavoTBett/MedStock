package com.app.medStock.controller;

import com.app.medStock.dto.item.ItemDto;
import com.app.medStock.dto.item.ItemInsert;
import com.app.medStock.model.Item;
import com.app.medStock.model.Product;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import java.math.BigDecimal;
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
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody ItemInsert entity) {
        try {
            Product product = productRepository.findById(entity.getProdutoId()).orElse(null);
            Item item = new Item(product, entity.getQuantidade(), entity.getPreco(), entity.getJuros(), entity.getDesconto());
            item = itemRepository.save(item);
            ItemDto itemDto = new ItemDto(item);
            return ResponseEntity.created(URI.create("api/item/" + itemDto.getId())).body(itemDto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Item.class) Predicate predicate) {
        try {
            List<Item> itens = (List<Item>) itemRepository.findAll(predicate);
            List<ItemDto> itensDto = new ArrayList<>();
            itens.forEach(action -> {
                itensDto.add(new ItemDto(action));
            });
            return ResponseEntity.ok(itensDto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @GetMapping
    public ResponseEntity findAll() {
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

    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Item item = itemRepository.findById(id).orElse(null);
            ItemDto itemDto = new ItemDto(item);
            return ResponseEntity.ok(itemDto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }
}
