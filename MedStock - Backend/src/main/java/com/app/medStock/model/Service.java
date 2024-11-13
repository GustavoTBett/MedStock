package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
public class Service extends MasterEntity implements FinancialOperations {
    @Column(name = "service_date")
    private LocalDateTime serviceDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "type")
    private OrderType type = OrderType.SERVICE;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Service(LocalDateTime purchaseDate, Client client, String description, Item item, Employee employee) {
        this.serviceDate = purchaseDate;
        this.client = client;
        this.description = description;
        this.item = item;
        this.employee = employee;
    }
   
    @Override
    public LocalDateTime getOrderDate() {
        return this.serviceDate;
    }

    @Override
    public BigDecimal getTotalOrderValue() {
        return this.getItem().getFinalPrice();
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.SERVICE;
    }
}
