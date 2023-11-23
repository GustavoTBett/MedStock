package com.app.medStock.dto;

import com.app.medStock.model.Item;
import com.app.medStock.model.Product;
import java.math.BigDecimal;

/**
 *
 * @author gustavo
 */
public class ItemDto extends MasterEntityDto {
    private Product produto;
    private Long quantidade;
    private BigDecimal preco;
    private Double juros;
    private Double desconto;
    
    public ItemDto(Item item) {
        this.setId(item.getId());
        this.setCriado(item.getCreated());
        this.setVersao(item.getVersion());
        this.produto = item.getProduct();
        this.quantidade = item.getQuantity();
        this.preco = item.getPrice();
        this.juros = item.getFees();
        this.desconto = item.getDiscount();
    }

    public ItemDto() {
    }

    public ItemDto(Product produto, Long quantidade, BigDecimal preco, Double juros, Double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.juros = juros;
        this.desconto = desconto;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
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
