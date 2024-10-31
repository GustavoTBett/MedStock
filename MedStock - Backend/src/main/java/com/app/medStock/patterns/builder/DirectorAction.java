package com.app.medStock.patterns.builder;

import com.app.medStock.enums.OrderType;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class DirectorAction {
    private BuilderAction builderAction;

    public DirectorAction(BuilderAction builderAction) {
        this.builderAction = builderAction;
    }

    void createCompra(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.PURCHASE);
    }

    void createVenda(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.SALE);
    }

    void createServico(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.SERVICE);
    }
}
