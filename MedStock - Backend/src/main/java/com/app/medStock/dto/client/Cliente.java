package com.app.medStock.dto.client;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.State;
import com.app.medStock.model.Client;

/**
 *
 * @author gustavo
 */
public class Cliente extends MasterEntityDto {
    private String nome;
    private String email;
    private Long telefone;
    private String cpf;
    private String cep;
    private String endereco;
    private State estado;
    
    public Cliente(Client client) {
        this.setId(client.getId());
        this.setCriado(client.getCreatedAt());
        this.setVersao(client.getVersion());
        this.nome = client.getName();
        this.email = client.getEmail();
        this.telefone = client.getPhone();
        this.cpf = client.getCpf();
        this.cep = client.getZipcode();
        this.endereco = client.getAddress();
        this.estado = client.getState();
    }
    
}
