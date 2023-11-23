package com.app.medStock.dto;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;

/**
 *
 * @author gustavo
 */
public class Estoque extends MasterEntityDto {
    private Product produto;
    private Batch lote;
    private Long quantidade;
    
    public Estoque(Stock stock) {
        this.setId(stock.getId());
        this.setCriado(stock.getCreated());
        this.setVersao(stock.getVersion());
        this.produto = stock.getProduct();
        this.lote = stock.getBatch();
        this.quantidade = stock.getQuantity();
    }

    public Estoque() {
    }

    public Estoque(Product produto, Batch lote, Long quantidade) {
        this.produto = produto;
        this.lote = lote;
        this.quantidade = quantidade;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public Batch getLote() {
        return lote;
    }

    public void setLote(Batch lote) {
        this.lote = lote;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
