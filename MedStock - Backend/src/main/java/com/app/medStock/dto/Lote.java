package com.app.medStock.dto;

import com.app.medStock.model.Batch;
import java.time.LocalDate;

/**
 *
 * @author gustavo
 */
public class Lote extends MasterEntityDto{
    private Long numero;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Long produtoId;
    
    public Lote(Batch batch) {
        this.setId(batch.getId());
        this.setCriado(batch.getCreated());
        this.setVersao(batch.getVersion());
        this.numero = batch.getNumber();
        this.dataFabricacao = batch.getFabricationDate();
        this.dataValidade = batch.getValidDate();
        this.produtoId = batch.getProduct().getId();
    }

    public Lote() {
    }

    public Lote(Long numero, LocalDate dataFabricacao, LocalDate dataValidade, Long produtoId) {
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
