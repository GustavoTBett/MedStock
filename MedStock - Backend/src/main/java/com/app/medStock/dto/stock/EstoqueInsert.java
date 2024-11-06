package com.app.medStock.dto.stock;

import com.app.medStock.model.Stock;
import lombok.Getter;

/**
 *
 * @author gustavo
 */
@Getter
public class EstoqueInsert {
    private Long produtoId;
    private Long loteId;
    private Long quantidade;
    
}
