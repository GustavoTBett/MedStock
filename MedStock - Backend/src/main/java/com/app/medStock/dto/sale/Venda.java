package com.app.medStock.dto.sale;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Sale;
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
public class Venda extends MasterEntityDto implements BuilderAction {
    private ActionGenerator actionGenerator;
    private Client client;
    private List<Item> itens;
    private Employee funcionario;
    
    public Venda(Sale sale) {
        this.setId(sale.getId());
        this.setCriado(sale.getCreatedAt());
        this.setVersao(sale.getVersion());
        actionGenerator.setOrderType(sale.getOrderType());
        actionGenerator.setDate(sale.getSaleDate());
        this.client = sale.getClient();
        this.itens = sale.getItens();
        this.funcionario = sale.getEmployee();
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
