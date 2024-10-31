package com.app.medStock.patterns.builder;

import com.app.medStock.enums.OrderType;

import java.time.LocalDateTime;

public interface BuilderAction {
    ActionGenerator actionGenerator();

    void setDate(LocalDateTime date);

    void setType(OrderType type);
}
