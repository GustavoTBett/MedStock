package com.app.medStock.dto.stock;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.model.Stock;

/**
 *
 * @author gustavo
 */
public class Estoque extends MasterEntityDto {
    private Long produtoId;
    private Long loteId;
    private Long quantidade;
    
    public Estoque(Stock stock) {
        this.setId(stock.getId());
        this.setCriado(stock.getCreatedAt());
        this.setVersao(stock.getVersion());
        this.produtoId = stock.getProduct().getId();
        this.loteId = stock.getBatch().getId();
        this.quantidade = stock.getQuantity();
    }
    
}
