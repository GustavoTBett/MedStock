package com.app.medStock.dto.sale;

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
