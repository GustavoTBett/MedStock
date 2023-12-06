package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
import com.app.medStock.dto.sale.Venda;
import com.app.medStock.dto.sale.VendaInsert;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Sale;
import com.app.medStock.repository.ClientRepository;
import com.app.medStock.repository.EmployeeRepository;
import com.app.medStock.repository.ItemRepository;
import com.app.medStock.repository.SaleRepository;
import com.app.medStock.service.ItemService;
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
    public ResponseEntity create(@RequestBody VendaInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                Client client = clientRepository.findById(entity.getClientId()).orElse(null);
                List<Item> itens = new ArrayList<>();
                entity.getItensId().forEach(action -> {
                    itens.add(itemService.findByItemWithQuantity(action));
                });
                Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
                Sale sale = new Sale(entity.getDataVenda(), client, itens, employee);
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
                sale.setSaleDate(entity.getDataVenda());
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

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Sale.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
            try {
                List<Sale> sales = (List<Sale>) saleRepository.findAll(predicate);
                List<Venda> vendas = new ArrayList<>();
                sales.forEach(action -> {
                    vendas.add(new Venda(action));
                });
                return ResponseEntity.ok(sales);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
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
