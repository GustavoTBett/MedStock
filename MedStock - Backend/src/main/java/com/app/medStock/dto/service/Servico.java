package com.app.medStock.dto.service;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Service;
import com.app.medStock.patterns.builder.ActionGenerator;
import com.app.medStock.patterns.builder.BuilderAction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gustavo
 */
@Getter
@Setter
public class Servico extends MasterEntityDto implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Client client;
    private Item item;
    private Employee funcionario;
    private String descricao;

    public Servico() {
        this.actionGenerator = new ActionGenerator();
    }

    public Servico(Service service) {
        this.actionGenerator = new ActionGenerator();
        this.setId(service.getId());
        this.setCriado(service.getCreatedAt());
        this.setVersao(service.getVersion());
        actionGenerator.setOrderType(service.getOrderType());
        this.client = service.getClient();
        actionGenerator.setDate(service.getOrderDate());
        this.item = service.getItem();
        this.descricao = service.getDescription();
        this.funcionario = service.getEmployee();
    }

    @Override
    public ActionGenerator actionGenerator() {
        return actionGenerator;
    }

    @Override
    public void setDate(LocalDateTime date) {
        actionGenerator.setDate(date);
    }

    @Override
    public void setType(OrderType type) {
        actionGenerator.setOrderType(type);
    }
}
