package com.app.medStock.controller;

import com.app.medStock.dto.employee.Funcionario;
import com.app.medStock.dto.employee.FuncionarioInsert;
import com.app.medStock.model.Employee;
import com.app.medStock.repository.EmployeeRepository;
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
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody FuncionarioInsert entity) {
        try {
            Employee employee = new Employee(entity.getNome(), entity.getFuncao(), entity.getDataContratacao(), entity.getSalario(), entity.getEmail(),
                    entity.getTelefone(), entity.getCpf(), entity.getCep(), entity.getEndereco(), entity.getEstado());
            employee = employeeRepository.save(employee);
            Funcionario funcionario = new Funcionario(employee);
            return ResponseEntity.created(URI.create("api/employee/" + funcionario.getId())).body(funcionario);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FuncionarioInsert entity) {
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
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Employee.class) Predicate predicate) {
        try {
            List<Employee> employees = (List<Employee>) employeeRepository.findAll(predicate);
            List<Funcionario> funcionarios = new ArrayList<>();
            employees.forEach(action -> {
                funcionarios.add(new Funcionario(action));
            });
            return ResponseEntity.ok(funcionarios);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
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

    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            Funcionario funcionario = new Funcionario(employee);
            return ResponseEntity.ok(funcionario);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }
}
