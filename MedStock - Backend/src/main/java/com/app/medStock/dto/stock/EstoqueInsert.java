package com.app.medStock.dto.stock;

import com.app.medStock.model.Stock;

/**
 *
 * @author gustavo
 */
public class EstoqueInsert {
    private Long produtoId;
    private Long loteId;
    private Long quantidade;
    
    public EstoqueInsert(Stock stock) {
        this.produtoId = stock.getProduct().getId();
        this.loteId = stock.getBatch().getId();
        this.quantidade = stock.getQuantity();
    }

    public EstoqueInsert() {
    }

    public EstoqueInsert(Long produtoId, Long loteId, Long quantidade) {
        this.produtoId = produtoId;
        this.loteId = loteId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getLoteId() {
        return loteId;
    }

    public void setLoteId(Long loteId) {
        this.loteId = loteId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    
}
