package com.app.medStock.patterns.builder.purchase;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Item;
import com.app.medStock.model.Provider;
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
public class Compra extends MasterEntityDto implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Provider fornecedor;
    private List<Item> itens;

    public Compra() {
        actionGenerator = new ActionGenerator();
    }

    public Compra(ActionGenerator actionGenerator, Provider fornecedor, List<Item> itens) {
        this.actionGenerator = actionGenerator;
        this.fornecedor = fornecedor;
        this.itens = itens;
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
