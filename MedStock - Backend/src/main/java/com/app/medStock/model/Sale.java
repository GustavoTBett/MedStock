package com.app.medStock.model;

import com.app.medStock.enums.OrderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends MasterEntity implements FinancialOperations{
    @Column(name = "sale_date")
    private LocalDateTime saleDate;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "type")
    private OrderType type = OrderType.SALE;
    @OneToMany(mappedBy = "product")
    private List<Item> itens = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

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
