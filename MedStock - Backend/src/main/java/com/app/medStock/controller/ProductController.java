package com.app.medStock.controller;

import com.app.medStock.dto.product.Produto;
import com.app.medStock.dto.product.ProdutoInsert;
import com.app.medStock.model.Product;
import com.app.medStock.repository.ProductRepository;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody ProdutoInsert entity) {
        try {
            Product product = new Product(entity.getNome(), entity.getDescricao(), entity.getCodigo(), entity.getCategoria(), entity.getFabricante());
            product = productRepository.save(product);
            Produto produto = new Produto(product);
            return ResponseEntity.created(URI.create("api/product/" + produto.getId())).body(produto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProdutoInsert entity) {
        try {
            Product product = productRepository.findById(id).get();
            product.setName(entity.getNome());
            product.setDescription(entity.getDescricao());
            product.setCode(entity.getCodigo());
            product.setCategory(product.getCategory());
            product.setProducer(entity.getFabricante());
            product = productRepository.save(product);
            Produto produto = new Produto(product);
            return ResponseEntity.ok(produto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Product.class) Predicate predicate) {
        try {
            List<Product> products = (List<Product>) productRepository.findAll(predicate);
            List<Produto> produtos = new ArrayList<>();
            products.forEach(action -> {
                produtos.add(new Produto(action));
            });
            return ResponseEntity.ok(products);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            List<Product> products = productRepository.findAll();
            List<Produto> produtos = new ArrayList<>();
            products.forEach(action -> {
                produtos.add(new Produto(action));
            });
            return ResponseEntity.ok(produtos);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            Produto produto = new Produto(product);
            return ResponseEntity.ok(produto);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
}
