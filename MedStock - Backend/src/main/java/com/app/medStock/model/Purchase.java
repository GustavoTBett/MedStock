package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
public class Purchase extends MasterEntity implements FinancialOperations {
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @Column(name = "type")
    private OrderType type = OrderType.PURCHASE;
    @ManyToMany
    private List<Item> itens = new ArrayList<>();

    public Purchase(LocalDateTime purchaseDate, Provider provider, List<Item> itens) {
        this.purchaseDate = purchaseDate;
        this.provider = provider;
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
