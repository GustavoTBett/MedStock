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
    
    public Lote(Batch batch) {
        this.setId(batch.getId());
        this.setCriado(batch.getCreatedAt());
        this.setVersao(batch.getVersion());
        this.numero = batch.getNumber();
        this.dataFabricacao = batch.getFabricationDate();
        this.dataValidade = batch.getValidDate();
    }

    
}
