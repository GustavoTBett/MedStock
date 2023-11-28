package com.app.medStock.controller;

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
    private ItemRepository itemRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody VendaInsert entity) {
        try {
            Client client = clientRepository.findById(entity.getClientId()).orElse(null);
            List<Item> itens = new ArrayList<>();
            entity.getItensId().forEach(action -> {
                itens.add(itemRepository.findById(action).orElse(null));
            });
            Employee employee = employeeRepository.findById(entity.getFuncionarioId()).orElse(null);
            Sale sale = new Sale(entity.getDataVenda(), client, itens, employee);
            sale = saleRepository.save(sale);
            Venda venda = new Venda(sale);
            return ResponseEntity.created(URI.create("api/sale/" + venda.getId())).body(venda);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VendaInsert entity) {
        try {
            Sale sale = saleRepository.findById(id).get();
            Client client = clientRepository.findById(entity.getClientId()).orElse(null);
            List<Item> itens = new ArrayList<>();
            entity.getItensId().forEach(action -> {
                itens.add(itemRepository.findById(action).orElse(null));
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
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = Sale.class) Predicate predicate) {
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

    }

    @GetMapping
    public ResponseEntity findAll() {
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
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            Sale sale = saleRepository.findById(id).orElse(null);
            Venda venda = new Venda(sale);
            return ResponseEntity.ok(venda);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            saleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }
}
