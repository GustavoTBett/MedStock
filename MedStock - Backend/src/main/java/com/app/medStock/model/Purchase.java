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
public class Purchase extends MasterEntity implements FinancialOperations{
    private LocalDateTime purchaseDate;
    private Provider provider;
    private OrderType type = OrderType.PURCHASE;
    
    private List<Item> itens = new ArrayList<>();

    public Purchase() {
    }

    public Purchase(LocalDateTime purchaseDate, Provider provider, List<Item> itens) {
        this.purchaseDate = purchaseDate;
        this.provider = provider;
        this.itens = itens;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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
        return this.purchaseDate;
    }

    @Override
    public BigDecimal getTotalOrderValue() {
        return BigDecimal.ONE;
    }

    @Override
    public OrderType getOrderType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
