package com.app.medStock.dto.employee;

import com.app.medStock.enums.Functions;
import com.app.medStock.enums.State;
import com.app.medStock.model.Employee;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gustavo
 */
@Getter
public class FuncionarioInsert {
    private String nome;
    private Functions funcao;
    private LocalDate dataContratacao;
    private BigDecimal salario;
    private String email;
    private Long telefone;
    private String cpf;
    private String cep;
    private String endereco;
    private State estado;
}
