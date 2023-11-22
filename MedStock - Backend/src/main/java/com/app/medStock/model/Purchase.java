package com.app.medStock.model;

import com.app.medStock.enums.OrderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "purchase")
public class Purchase extends MasterEntity implements FinancialOperations{
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @Column(name = "type")
    private OrderType type = OrderType.PURCHASE;
    @OneToMany(mappedBy = "product")
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
        return BigDecimal.valueOf(this.getItens().stream().mapToDouble(item -> item.getFinalPrice().doubleValue()).sum());
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.PURCHASE;
    }
}
