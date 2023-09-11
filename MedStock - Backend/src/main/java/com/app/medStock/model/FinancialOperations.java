package com.app.medStock.model;

import com.app.medStock.enums.OrderType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author gusta
 */
public interface FinancialOperations {
    public LocalDateTime getOrderDate();

    public BigDecimal getTotalOrderValue();

    public OrderType getOrderType();
}
