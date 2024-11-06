package com.app.medStock.dto.batch;

import com.app.medStock.model.Batch;

import java.time.LocalDate;

/**
 *
 * @author gustavo
 */
public class LoteInsert {
    private Long numero;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Long produtoId;
    
    public LoteInsert(Batch batch) {
        this.numero = batch.getNumber();
        this.dataFabricacao = batch.getFabricationDate();
        this.dataValidade = batch.getValidDate();
        this.produtoId = batch.getProduct().getId();
    }

    public LoteInsert() {
    }

    public LoteInsert(Long numero, LocalDate dataFabricacao, LocalDate dataValidade, Long produtoId) {
        this.numero = numero;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.produtoId = produtoId;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    
}
