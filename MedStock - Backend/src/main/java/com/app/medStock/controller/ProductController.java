package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.product.Produto;
import com.app.medStock.dto.product.ProdutoInsert;
import com.app.medStock.model.Product;
import com.app.medStock.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    @Operation(summary = "Criar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar produto", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity create(@RequestBody ProdutoInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Product product = new Product(entity.getNome(), entity.getDescricao(), entity.getCodigo(), entity.getCategoria(), entity.getFabricante());
                product = productRepository.save(product);
                Produto produto = new Produto(product);
                return ResponseEntity.created(URI.create("api/product/" + produto.getId())).body(produto);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar produto", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProdutoInsert entity) {
        if (rateLimiter.tryAcquire()) {
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
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
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
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Product product = productRepository.findById(id).orElse(null);
                Produto produto = new Produto(product);
                return ResponseEntity.ok(produto);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                productRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
