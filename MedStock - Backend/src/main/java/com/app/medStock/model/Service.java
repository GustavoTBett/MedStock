package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author gusta
 */
public class Service extends MasterEntity{
    private LocalDateTime purchaseDate;
    private Client client;
    private OrderType type = OrderType.SERVICE;
    private BigDecimal originalPrice;
    private BigDecimal fees;
    private BigDecimal discount;
    private String description;

    public Service() {
    }

    public Service(LocalDateTime purchaseDate, Client client, BigDecimal originalPrice, BigDecimal fees, BigDecimal discount, String description) {
        this.purchaseDate = purchaseDate;
        this.client = client;
        this.originalPrice = originalPrice;
        this.fees = fees;
        this.discount = discount;
        this.description = description;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
