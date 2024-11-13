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
public class VendaInsert {
    private LocalDateTime data;
    private Long clientId;
    private List<Long> itensId;
    private Long funcionarioId;

    public VendaInsert(LocalDateTime data, Long clientId, List<Long> itensId, Long funcionarioId) {
        this.data = data;
        this.clientId = clientId;
        this.itensId = itensId;
        this.funcionarioId = funcionarioId;
    }
}
