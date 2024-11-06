package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
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

    public Sale(LocalDateTime purchaseDate, Client client, List<Item> itens, Employee employee) {
        this.saleDate = purchaseDate;
        this.client = client;
        this.itens = itens;
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
