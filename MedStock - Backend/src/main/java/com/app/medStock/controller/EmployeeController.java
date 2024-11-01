package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.patterns.bridge.employee.Funcionario;
import com.app.medStock.patterns.bridge.employee.FuncionarioInsert;
import com.app.medStock.model.Employee;
import com.app.medStock.repository.EmployeeRepository;
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
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    @Operation(summary = "Criar funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao criar funcionário", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity create(@RequestBody FuncionarioInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Employee employee = new Employee(entity.getNome(), entity.getFuncao(), entity.getDataContratacao(), entity.getSalario(), entity.getEmail(),
                        entity.getTelefone(), entity.getCpf(), entity.getCep(), entity.getEndereco(), entity.getEstado());
                employee = employeeRepository.save(employee);
                Funcionario funcionario = new Funcionario(employee);
                return ResponseEntity.created(URI.create("api/employee/" + funcionario.getId())).body(funcionario);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar funcionário", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FuncionarioInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Employee employee = employeeRepository.findById(id).get();
                employee.setName(entity.getNome());
                employee.setFunctions(entity.getFuncao());
                employee.setHiringDate(entity.getDataContratacao());
                employee.setSalary(entity.getSalario());
                employee.setEmail(entity.getEmail());
                employee.setPhone(entity.getTelefone());
                employee.setCpf(entity.getCpf());
                employee.setZipcode(entity.getCep());
                employee.setAddress(entity.getEndereco());
                employee.setState(entity.getEstado());
                employee = employeeRepository.save(employee);
                Funcionario funcionario = new Funcionario(employee);
                return ResponseEntity.ok(funcionario);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de funcionários",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))}),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Employee> employees = employeeRepository.findAll();
                List<Funcionario> funcionarios = new ArrayList<>();
                employees.forEach(action -> {
                    funcionarios.add(new Funcionario(action));
                });
                return ResponseEntity.ok(funcionarios);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))}),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                Employee employee = employeeRepository.findById(id).orElse(null);
                Funcionario funcionario = new Funcionario(employee);
                return ResponseEntity.ok(funcionario);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content),
            @ApiResponse(responseCode = "429", description = "Muitas solicitações", content = @Content)
    })
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                employeeRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
