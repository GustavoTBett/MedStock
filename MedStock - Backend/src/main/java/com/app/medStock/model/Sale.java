package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class Sale extends MasterEntity{
    private LocalDateTime purchaseDate;
    private Client client;
    private OrderType type = OrderType.SALE;
    private BigDecimal originalPrice;
    private BigDecimal fees;
    private BigDecimal discount;
    private List<Item> itens = new ArrayList<>();

    public Sale() {
    }

    public Sale(LocalDateTime purchaseDate, Client client, BigDecimal originalPrice, BigDecimal fees, BigDecimal discount, List<Item> itens) {
        this.purchaseDate = purchaseDate;
        this.client = client;
        this.originalPrice = originalPrice;
        this.fees = fees;
        this.discount = discount;
        this.itens = itens;
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
