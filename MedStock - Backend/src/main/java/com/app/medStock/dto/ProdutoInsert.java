package com.app.medStock.dto;

import com.app.medStock.model.Product;

/**
 *
 * @author gustavo
 */
public class ProdutoInsert {
    private String nome;
    private String descricao;
    private Long codigo;
    private String categoria;
    private String fabricante;
    
    public ProdutoInsert(Product product) {
        this.nome = product.getName();
        this.descricao = product.getDescription();
        this.codigo = product.getCode();
        this.categoria = product.getCategory();
        this.fabricante = product.getProducer();
    }

    public ProdutoInsert() {
    }

    public ProdutoInsert(String nome, String descricao, Long codigo, String categoria, String fabricante) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.categoria = categoria;
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    
}
