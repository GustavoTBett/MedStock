package com.app.medStock.dto.client;

import com.app.medStock.enums.State;
import com.app.medStock.model.Client;
import lombok.Getter;

/**
 *
 * @author gustavo
 */
@Getter
public class ClienteInsert{
    private String nome;
    private String email;
    private Long telefone;
    private String cpf;
    private String cep;
    private String endereco;
    private State estado;
    
}
