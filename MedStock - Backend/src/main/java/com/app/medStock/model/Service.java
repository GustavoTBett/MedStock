package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "service")
public class Service extends MasterEntity implements FinancialOperations{
    @Column(name = "service_date")
    private LocalDateTime serviceDate;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "type")
    private OrderType type = OrderType.SERVICE;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Service() {
    }

    public Service(LocalDateTime purchaseDate, Client client, String description, Item item, Employee employee) {
        this.serviceDate = purchaseDate;
        this.client = client;
        this.description = description;
        this.item = item;
        this.employee = employee;
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDateTime serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
