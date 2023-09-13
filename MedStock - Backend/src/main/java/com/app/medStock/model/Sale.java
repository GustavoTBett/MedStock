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
public class Sale extends MasterEntity implements FinancialOperations{
    private LocalDateTime saleDate;
    private Client client;
    private OrderType type = OrderType.SALE;
    private List<Item> itens = new ArrayList<>();

    public Sale() {
    }

    public Sale(LocalDateTime purchaseDate, Client client, List<Item> itens) {
        this.saleDate = purchaseDate;
        this.client = client;
        this.itens = itens;
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
