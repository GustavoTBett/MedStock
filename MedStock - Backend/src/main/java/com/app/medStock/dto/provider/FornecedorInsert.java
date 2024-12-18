package com.app.medStock.dto.provider;

import com.app.medStock.enums.State;
import com.app.medStock.model.Provider;
import lombok.Getter;

/**
 *
 * @author gustavo
 */
@Getter
public class FornecedorInsert {
    private String nome;
    private String email;
    private Long telefone;
    private String cnpj;
    private String cep;
    private String endereco;
    private State estado;
    
}
