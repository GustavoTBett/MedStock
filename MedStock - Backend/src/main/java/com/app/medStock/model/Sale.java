package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "sale")
public class Sale extends MasterEntity implements FinancialOperations {
    @Column(name = "sale_date")
    private LocalDateTime saleDate;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "type")
    private OrderType type = OrderType.SALE;
    @OneToMany
    private List<Item> itens = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Sale() {
    }

    public Sale(LocalDateTime purchaseDate, Client client, List<Item> itens, Employee employee) {
        this.saleDate = purchaseDate;
        this.client = client;
        this.itens = itens;
        this.employee = employee;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderType getType() {
        return type;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public LocalDateTime getOrderDate() {
        return this.saleDate;
    }

    @Override
    public BigDecimal getTotalOrderValue() {
        return BigDecimal.valueOf(this.getItens().stream().mapToDouble(item -> item.getFinalPrice().doubleValue()).sum());
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.SALE;
    }
}
