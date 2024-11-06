package com.app.medStock.dto.service;

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
