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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
