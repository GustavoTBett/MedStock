package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author gusta
 */
public class Service extends MasterEntity implements FinancialOperations{
    private LocalDateTime serviceDate;
    private Client client;
    private OrderType type = OrderType.SERVICE;
    private Item item;
    private String description;

    public Service() {
    }

    public Service(LocalDateTime purchaseDate, Client client, String description) {
        this.serviceDate = purchaseDate;
        this.client = client;
        this.description = description;
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
