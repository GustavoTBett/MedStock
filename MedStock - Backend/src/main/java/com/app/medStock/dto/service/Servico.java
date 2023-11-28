package com.app.medStock.dto.service;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Service;
import java.time.LocalDateTime;

/**
 *
 * @author gustavo
 */
public class Servico extends MasterEntityDto{
    private LocalDateTime dataServico;
    private Long clienteId;
    private OrderType tipo;
    private Long itemId;
    private String descricao;
    private Long funcionarioId;
    
    public Servico(Service service) {
        this.setId(service.getId());
        this.setCriado(service.getCreated());
        this.setVersao(service.getVersion());
        this.dataServico = service.getServiceDate();
        this.clienteId = service.getClient().getId();
        this.tipo = service.getType();
        this.itemId = service.getItem().getId();
        this.descricao = service.getDescription();
        this.funcionarioId = service.getEmployee().getId();
    }

    public Servico() {
    }

    public Servico(LocalDateTime dataServico, Long clienteId, OrderType tipo, Long itemId, String descricao, Long funcionarioId) {
        this.dataServico = dataServico;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.itemId = itemId;
        this.descricao = descricao;
        this.funcionarioId = funcionarioId;
    }

    public LocalDateTime getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDateTime dataServico) {
        this.dataServico = dataServico;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public OrderType getTipo() {
        return tipo;
    }

    public void setTipo(OrderType tipo) {
        this.tipo = tipo;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    
}
