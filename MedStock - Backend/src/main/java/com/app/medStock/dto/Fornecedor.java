package com.app.medStock.dto;

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
        this.setCriado(provider.getCreated());
        this.setVersao(provider.getVersion());
        this.nome = provider.getName();
        this.email = provider.getEmail();
        this.telefone = provider.getPhone();
        this.cnpj = provider.getCnpj();
        this.cep = provider.getZipcode();
        this.endereco = provider.getAddress();
        this.estado = provider.getState();
    }

    public Fornecedor() {
    }

    public Fornecedor(String nome, String email, Long telefone, String cnpj, String cep, String endereco, State estado) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }
    
    
}
