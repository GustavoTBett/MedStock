package com.app.medStock.dto.batch;

import com.app.medStock.model.Batch;
import lombok.Getter;

import java.time.LocalDate;

/**
 *
 * @author gustavo
 */
@Getter
public class LoteInsert {
    private Long numero;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    
}
