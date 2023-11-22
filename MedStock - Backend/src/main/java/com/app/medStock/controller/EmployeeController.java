package com.app.medStock.controller;

import com.app.medStock.model.Employee;
import com.app.medStock.repository.EmployeeRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @PostMapping
    public ResponseEntity create(@RequestBody Employee entity) {
        Employee save = employeeRepository.save(entity);
        return ResponseEntity.created(URI.create("api/employee/" + entity.getId())).body(save);
    }
    
    @GetMapping
    public ResponseEntity findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return ResponseEntity.ok(employee);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
