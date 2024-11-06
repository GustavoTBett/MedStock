package com.app.medStock.patterns.builder.sale;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Sale;
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
public class VendaInsert implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Long clientId;
    private List<Long> itensId;
    private Long funcionarioId;

    public VendaInsert(ActionGenerator actionGenerator, Long clientId, List<Long> itensId, Long funcionarioId) {
        this.actionGenerator = actionGenerator;
        this.clientId = clientId;
        this.itensId = itensId;
        this.funcionarioId = funcionarioId;
    }

    public VendaInsert(Sale sale) {
        actionGenerator.setOrderType(sale.getOrderType());
        actionGenerator.setDate(sale.getOrderDate());
        this.clientId = sale.getClient().getId();
        this.itensId = sale.getItens().stream().map(x -> x.getId()).toList();
        this.funcionarioId = sale.getEmployee().getId();
    }

    @Override
    public ActionGenerator actionGenerator() {
        return actionGenerator;
    }

    @Override
    public void setDate(LocalDateTime date) {

    }

    @Override
    public void setType(OrderType type) {

    }
}
