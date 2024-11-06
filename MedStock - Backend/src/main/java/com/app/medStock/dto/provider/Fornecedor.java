package com.app.medStock.dto.provider;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.State;
import com.app.medStock.model.Provider;

/**
 *
 * @author gustavo
 */
public class Fornecedor extends MasterEntityDto {
    private String nome;
    private String email;
    private Long telefone;
    private String cnpj;
    private String cep;
    private String endereco;
    private State estado;
    
    public Fornecedor(Provider provider) {
        this.setId(provider.getId());
        this.setCriado(provider.getCreatedAt());
        this.setVersao(provider.getVersion());
        this.nome = provider.getName();
        this.email = provider.getEmail();
        this.telefone = provider.getPhone();
        this.cnpj = provider.getCnpj();
        this.cep = provider.getZipcode();
        this.endereco = provider.getAddress();
        this.estado = provider.getState();
    }
    
}
