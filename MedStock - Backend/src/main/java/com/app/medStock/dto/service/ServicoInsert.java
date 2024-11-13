package com.app.medStock.dto.service;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Service;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.patterns.builder.BuilderAction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author gustavo
 */
@Getter
@Setter
public class ServicoInsert {
    private LocalDateTime data;
    private Long clienteId;
    private Long itemId;
    private String descricao;
    private Long funcionarioId;
}
