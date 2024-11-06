package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Service;
import com.app.medStock.dto.service.Servico;
import com.app.medStock.dto.service.ServicoInsert;
import com.app.medStock.repository.ClientRepository;
import com.app.medStock.repository.EmployeeRepository;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ServiceRepository;
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
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    @Operation(summary = "Criar serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar serviço", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity create(@RequestBody ServicoInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(entity.getClienteId()).orElse(null);
                Item item = itemRepository.findById(entity.getItemId()).orElse(null);
                Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
                Service save = new Service(entity.getActionGenerator().getDate(), client, entity.getDescricao(), item, employee);
                save = serviceRepository.save(save);
                Servico servico = new Servico(save);
                return ResponseEntity.created(URI.create("api/service/" + servico.getId())).body(servico);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar serviço", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ServicoInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(entity.getClienteId()).orElse(null);
                Item item = itemRepository.findById(entity.getItemId()).orElse(null);
                Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
                Service service = serviceRepository.findById(id).get();
                service.setServiceDate(entity.getActionGenerator().getDate());
                service.setClient(client);
                service.setItem(item);
                service.setDescription(entity.getDescricao());
                service.setEmployee(employee);
                Servico servico = new Servico(service);
                return ResponseEntity.ok(servico);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    @Operation(summary = "Buscar serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))}),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Service> service = (List<Service>) serviceRepository.findAll();
                List<Servico> servicos = new ArrayList<>();
                service.forEach(action -> {
                    servicos.add(new Servico(action));
                });
                return ResponseEntity.ok(servicos);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))}),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Service service = serviceRepository.findById(id).orElse(null);
                Servico servico = new Servico(service);
                return ResponseEntity.ok(servico);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                serviceRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
