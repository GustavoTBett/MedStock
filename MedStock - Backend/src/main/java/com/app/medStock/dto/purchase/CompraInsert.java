package com.app.medStock.dto.purchase;

import com.app.medStock.enums.OrderType;
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
public class CompraInsert {
    private LocalDateTime date;
    private Long fornecedorId;
    private List<Long> itensId;

    public CompraInsert(LocalDateTime date, Long fornecedorId, List<Long> itensId) {
        this.date = date;
        this.fornecedorId = fornecedorId;
        this.itensId = itensId;
    }
}
