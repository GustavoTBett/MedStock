package com.app.medStock.controller;

import com.app.medStock.dto.service.Servico;
import com.app.medStock.dto.service.ServicoInsert;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Service;
import com.app.medStock.repository.ClientRepository;
import com.app.medStock.repository.EmployeeRepository;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.ServiceRepository;
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

    @PostMapping
    public ResponseEntity create(@RequestBody ServicoInsert entity) {
        try {
            Client client = clientRepository.findById(entity.getClienteId()).orElse(null);
            Item item = itemRepository.findById(entity.getItemId()).orElse(null);
            Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
            Service save = new Service(entity.getDataServico(), client, entity.getDescricao(), item, employee);
            save = serviceRepository.save(save);
            Servico servico = new Servico(save);
            return ResponseEntity.created(URI.create("api/service/" + servico.getId())).body(servico);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ServicoInsert entity) {
        try {
            Client client = clientRepository.findById(entity.getClienteId()).orElse(null);
            Item item = itemRepository.findById(entity.getItemId()).orElse(null);
            Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
            Service service = serviceRepository.findById(id).get();
            service.setServiceDate(entity.getDataServico());
            service.setClient(client);
            service.setItem(item);
            service.setDescription(entity.getDescricao());
            service.setEmployee(employee);
            Servico servico = new Servico(service);
            return ResponseEntity.ok(servico);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Service.class) Predicate predicate) {
        try {
            List<Service> service = (List<Service>) serviceRepository.findAll(predicate);
            List<Servico> servicos = new ArrayList<>();
            service.forEach(action -> {
                servicos.add(new Servico(action));
            });
            return ResponseEntity.ok(servicos);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @GetMapping
    public ResponseEntity findAll() {
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
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Service service = serviceRepository.findById(id).orElse(null);
            Servico servico = new Servico(service);
            return ResponseEntity.ok(servico);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            serviceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }
}
