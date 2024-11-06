package com.app.medStock.patterns.builder.service;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Service;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.patterns.builder.BuilderAction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author gustavo
 */
@Getter
@Setter
public class ServicoInsert implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Long clienteId;
    private Long itemId;
    private String descricao;
    private Long funcionarioId;
    
    public ServicoInsert(Service service) {
        this.actionGenerator.setDate(service.getOrderDate());
        this.actionGenerator.setOrderType(service.getOrderType());
        this.clienteId = service.getClient().getId();
        this.itemId = service.getItem().getId();
        this.descricao = service.getDescription();
        this.funcionarioId = service.getEmployee().getId();
    }

    public ServicoInsert(ActionGenerator actionGenerator, Long clienteId, Long itemId, String descricao, Long funcionarioId) {
        this.actionGenerator = actionGenerator;
        this.clienteId = clienteId;
        this.itemId = itemId;
        this.descricao = descricao;
        this.funcionarioId = funcionarioId;
    }

    @Override
    public ActionGenerator actionGenerator() {
        return actionGenerator;
    }

    @Override
    public void setDate(LocalDateTime date) {
        actionGenerator.setDate(date);
    }

    @Override
    public void setType(OrderType type) {
        actionGenerator.setOrderType(type);
    }
}
