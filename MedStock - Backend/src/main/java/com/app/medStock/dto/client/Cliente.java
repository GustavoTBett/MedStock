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
        this.setCriado(client.getCreated());
        this.setVersao(client.getVersion());
        this.nome = client.getName();
        this.email = client.getEmail();
        this.telefone = client.getPhone();
        this.cpf = client.getCpf();
        this.cep = client.getZipcode();
        this.endereco = client.getAddress();
        this.estado = client.getState();
    }

    public Cliente() {
    }

    public Cliente(String nome, String email, Long telefone, String cpf, String cep, String endereco, State estado) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
