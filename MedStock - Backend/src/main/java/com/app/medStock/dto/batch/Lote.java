package com.app.medStock.dto.batch;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.dto.product.Produto;
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
    private Produto produto;
    
    public Lote(Batch batch) {
        this.setId(batch.getId());
        this.setCriado(batch.getCreated());
        this.setVersao(batch.getVersion());
        this.numero = batch.getNumber();
        this.dataFabricacao = batch.getFabricationDate();
        this.dataValidade = batch.getValidDate();
        this.produto = new Produto(batch.getProduct());
    }

    public Lote() {
    }

    public Lote(Long numero, LocalDate dataFabricacao, LocalDate dataValidade, Produto produto) {
        this.numero = numero;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
}
