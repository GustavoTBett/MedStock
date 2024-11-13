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

    public void createCompra(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.PURCHASE);
    }

    public void createVenda(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.SALE);
    }

    public void createServico(LocalDateTime localDateTime) {
        builderAction.setDate(localDateTime);
        builderAction.setType(OrderType.SERVICE);
    }
}
