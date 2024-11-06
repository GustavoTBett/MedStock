package com.app.medStock.dto.employee;

import com.app.medStock.dto.MasterEntityDto;
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
        this.setCriado(employee.getCreatedAt());
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
    
}
