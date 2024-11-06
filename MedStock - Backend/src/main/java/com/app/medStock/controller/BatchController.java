package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.batch.Lote;
import com.app.medStock.dto.batch.LoteInsert;
import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.repository.BatchRepository;
import com.app.medStock.repository.ProductRepository;
import com.app.medStock.service.BatchService;
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
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @Operation(summary = "Criar lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lote.class))}),
            @ApiResponse(responseCode = "400", description = "Erro na criação do lote", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @PostMapping
    public ResponseEntity create(@RequestBody LoteInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Batch batch = new Batch(entity.getNumero(), entity.getDataFabricacao(), entity.getDataValidade());
                batch = batchRepository.save(batch);
                Lote loteInsert = new Lote(batch);
                return ResponseEntity.created(URI.create("api/batch/" + loteInsert.getId())).body(loteInsert);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Atualizar lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lote.class))}),
            @ApiResponse(responseCode = "400", description = "Erro na atualização do lote", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody LoteInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Batch batch = batchRepository.findById(id).get();
                batch.setNumber(entity.getNumero());
                batch.setFabricationDate(entity.getDataFabricacao());
                batch.setValidDate(entity.getDataValidade());
                batch = batchRepository.save(batch);
                Lote lote = new Lote(batch);
                return ResponseEntity.ok(lote);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }

    }

    @Operation(summary = "Listar todos os lotes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lote.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @GetMapping
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            List<Batch> batchs = batchRepository.findAll();
            List<Lote> lotes = new ArrayList<>();
            batchs.forEach(action -> {
                lotes.add(new Lote(action));
            });
            return ResponseEntity.ok(lotes);
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Buscar lote por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lote.class))}),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            Batch batch = batchService.findById(id);
            return ResponseEntity.ok(new Lote(batch));
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @Operation(summary = "Remover lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            batchRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
