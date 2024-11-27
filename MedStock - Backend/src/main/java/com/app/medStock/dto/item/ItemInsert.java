package com.app.medStock.dto.item;

import com.app.medStock.model.Item;
import lombok.Getter;

import java.math.BigDecimal;

/**
 *
 * @author gustavo
 */
@Getter
public class ItemInsert {
    private Long produtoId;
    private Long quantidade;
    private BigDecimal preco;
    private Double juros;
    private Double desconto;
    private Long estoqueId;
}
