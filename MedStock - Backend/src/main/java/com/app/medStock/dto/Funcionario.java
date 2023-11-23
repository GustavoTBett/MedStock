package com.app.medStock.dto;

import com.app.medStock.enums.Functions;
import com.app.medStock.enums.State;
import com.app.medStock.model.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gustavo
 */
public class Funcionario extends MasterEntityDto {
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
    
    public Funcionario(Employee employee) {
        this.setId(employee.getId());
        this.setCriado(employee.getCreated());
        this.setVersao(employee.getVersion());
        this.nome = employee.getName();
        this.funcao = employee.getFunctions();
        this.dataContratacao = employee.getHiringDate();
        this.salario = employee.getSalary();
        this.email = employee.getEmail();
        this.telefone = employee.getPhone();
        this.cpf = employee.getCpf();
        this.cep = employee.getZipcode();
        this.endereco = employee.getAddress();
        this.estado = employee.getState();
    }

    public Funcionario() {
    }

    public Funcionario(String nome, Functions funcao, LocalDate dataContratacao, BigDecimal salario, String email, Long telefone, String cpf, String cep, String endereco, State estado) {
        this.nome = nome;
        this.funcao = funcao;
        this.dataContratacao = dataContratacao;
        this.salario = salario;
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

    public Functions getFuncao() {
        return funcao;
    }

    public void setFuncao(Functions funcao) {
        this.funcao = funcao;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
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
