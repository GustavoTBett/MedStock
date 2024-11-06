package com.app.medStock.dto.item;

import com.app.medStock.model.Item;

import java.math.BigDecimal;

/**
 *
 * @author gustavo
 */
public class ItemInsert {
    private Long produtoId;
    private Long quantidade;
    private BigDecimal preco;
    private Double juros;
    private Double desconto;
    
    public ItemInsert(Item item) {
        this.produtoId = item.getProduct().getId();
        this.quantidade = item.getQuantity();
        this.preco = item.getPrice();
        this.juros = item.getFees();
        this.desconto = item.getDiscount();
    }

    public ItemInsert() {
    }

    public ItemInsert(Long produtoId, Long quantidade, BigDecimal preco, Double juros, Double desconto) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
        this.juros = juros;
        this.desconto = desconto;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    
}
