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
    @Operation(summary = "Criar estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar estoque", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Atualizar estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar estoque", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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

    @GetMapping
    @Operation(summary = "Listar todos os estoques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoques",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Buscar estoque por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))}),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
    @Operation(summary = "Remover estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estoque removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
