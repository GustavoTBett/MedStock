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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
