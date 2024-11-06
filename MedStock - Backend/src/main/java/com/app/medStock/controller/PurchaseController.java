package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.model.Item;
import com.app.medStock.model.Provider;
import com.app.medStock.model.Purchase;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.dto.purchase.Compra;
import com.app.medStock.dto.purchase.CompraInsert;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ProviderRepository;
import com.app.medStock.repository.PurchaseRepository;
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
    @Operation(summary = "Criar compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra criada com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar compra", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity create(@RequestBody CompraInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Provider provider = providerRepository.findById(entity.getFornecedorId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemRepository.findById(action).orElse(null));
                });
                Purchase purchase = new Purchase(entity.getActionGenerator().getDate(), provider, itens);
                purchase = purchaseRepository.save(purchase);
                ActionGenerator actionGenerator = new ActionGenerator();
                actionGenerator.setOrderType(purchase.getOrderType());
                actionGenerator.setDate(purchase.getOrderDate());
                Compra compra = new Compra(actionGenerator, purchase.getProvider(), purchase.getItens());
                return ResponseEntity.created(URI.create("api/purchase/" + compra.getId())).body(compra);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra atualizada com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar compra", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CompraInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Purchase purchase = purchaseRepository.findById(id).get();
                Provider provider = providerRepository.findById(entity.getFornecedorId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemRepository.findById(action).orElse(null));
                });
                purchase.setPurchaseDate(entity.getActionGenerator().getDate());
                purchase.setProvider(provider);
                purchase.setItens(itens);
                ActionGenerator actionGenerator = new ActionGenerator();
                actionGenerator.setOrderType(purchase.getOrderType());
                actionGenerator.setDate(purchase.getOrderDate());
                Compra compra = new Compra(actionGenerator, purchase.getProvider(), purchase.getItens());
                return ResponseEntity.ok(compra);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    @Operation(summary = "Listar todas as compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de compras",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Purchase> purchases = purchaseRepository.findAll();
                List<Compra> compras = new ArrayList<>();
                purchases.forEach(action -> {
                    ActionGenerator actionGenerator = new ActionGenerator();
                    actionGenerator.setOrderType(action.getOrderType());
                    actionGenerator.setDate(action.getOrderDate());

                    compras.add(new Compra(actionGenerator, action.getProvider(), action.getItens()));
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
    @Operation(summary = "Buscar compra por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Purchase purchase = purchaseRepository.findById(id).orElse(null);
                ActionGenerator actionGenerator = new ActionGenerator();
                actionGenerator.setOrderType(purchase.getOrderType());
                actionGenerator.setDate(purchase.getOrderDate());

                Compra compra = new Compra(actionGenerator, purchase.getProvider(), purchase.getItens());
                return ResponseEntity.ok(compra);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compra removida com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
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
