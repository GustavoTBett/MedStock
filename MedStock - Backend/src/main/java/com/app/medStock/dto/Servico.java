package com.app.medStock.dto;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Service;
import java.time.LocalDateTime;

/**
 *
 * @author gustavo
 */
public class Servico extends MasterEntityDto{
    private LocalDateTime dataServico;
    private Client cliente;
    private OrderType tipo;
    private Item item;
    private String descricao;
    private Employee funcionario;
    
    public Servico(Service service) {
        this.setId(service.getId());
        this.setCriado(service.getCreated());
        this.setVersao(service.getVersion());
        this.dataServico = service.getServiceDate();
        this.cliente = service.getClient();
        this.tipo = service.getType();
        this.item = service.getItem();
        this.descricao = service.getDescription();
        this.funcionario = service.getEmployee();
    }

    public Servico() {
    }

    public Servico(LocalDateTime dataServico, Client cliente, OrderType tipo, Item item, String descricao, Employee funcionario) {
        this.dataServico = dataServico;
        this.cliente = cliente;
        this.tipo = tipo;
        this.item = item;
        this.descricao = descricao;
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDateTime dataServico) {
        this.dataServico = dataServico;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public OrderType getTipo() {
        return tipo;
    }

    public void setTipo(OrderType tipo) {
        this.tipo = tipo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
