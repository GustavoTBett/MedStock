package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.patterns.builder.sale.Venda;
import com.app.medStock.patterns.builder.sale.VendaInsert;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Sale;
import com.app.medStock.repository.ClientRepository;
import com.app.medStock.repository.EmployeeRepository;
import com.app.medStock.repository.SaleRepository;
import com.app.medStock.service.ItemService;
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
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    @Operation(summary = "Criar venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda criada com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar venda", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity create(@RequestBody VendaInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(entity.getClientId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemService.findByItemWithQuantity(action));
                });
                Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
                Sale sale = new Sale(entity.getActionGenerator().getDate(), client, itens, employee);
                sale = saleRepository.save(sale);
                Venda venda = new Venda(sale);
                return ResponseEntity.created(URI.create("api/sale/" + venda.getId())).body(venda);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar venda", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VendaInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Sale sale = saleRepository.findById(id).get();
                Client client = clientRepository.findById(entity.getClientId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemService.findByItemWithQuantity(action));
                });
                Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
                sale.setSaleDate(entity.actionGenerator().getDate());
                sale.setClient(client);
                sale.setItens(itens);
                sale.setEmployee(employee);
                Venda venda = new Venda(sale);
                return ResponseEntity.ok(venda);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    @Operation(summary = "Listar todas as vendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendas",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Sale> sales = saleRepository.findAll();
                List<Venda> vendas = new ArrayList<>();
                sales.forEach(action -> {
                    vendas.add(new Venda(action));
                });
                return ResponseEntity.ok(vendas);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar venda por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))}),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Sale sale = saleRepository.findById(id).orElse(null);
                Venda venda = new Venda(sale);
                return ResponseEntity.ok(venda);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venda removida com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                saleRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
