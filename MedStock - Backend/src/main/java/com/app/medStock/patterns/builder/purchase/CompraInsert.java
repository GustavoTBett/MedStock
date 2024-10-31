package com.app.medStock.patterns.builder.purchase;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Purchase;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.patterns.builder.BuilderAction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gustavo
 */
@Getter
@Setter
public class CompraInsert implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Long fornecedorId;
    private List<Long> itensId;

    public CompraInsert(ActionGenerator actionGenerator, Long fornecedorId, List<Long> itensId) {
        this.actionGenerator = actionGenerator;
        this.fornecedorId = fornecedorId;
        this.itensId = itensId;
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
