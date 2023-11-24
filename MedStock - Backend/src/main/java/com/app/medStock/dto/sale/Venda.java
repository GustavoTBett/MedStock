package com.app.medStock.dto.sale;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Client;
import com.app.medStock.model.Employee;
import com.app.medStock.model.Item;
import com.app.medStock.model.Sale;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class Venda extends MasterEntityDto {
    private LocalDateTime dataVenda;
    private Client client;
    private OrderType tipo;
    private List<Item> itens;
    private Employee funcionario;
    
    public Venda(Sale sale) {
        this.setId(sale.getId());
        this.setCriado(sale.getCreated());
        this.setVersao(sale.getVersion());
        this.dataVenda = sale.getSaleDate();
        this.client = sale.getClient();
        this.tipo = sale.getType();
        this.itens = sale.getItens();
        this.funcionario = sale.getEmployee();
    }

    public Venda() {
    }

    public Venda(LocalDateTime dataVenda, Client client, OrderType tipo, List<Item> itens, Employee funcionario) {
        this.dataVenda = dataVenda;
        this.client = client;
        this.tipo = tipo;
        this.itens = itens;
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderType getTipo() {
        return tipo;
    }

    public void setTipo(OrderType tipo) {
        this.tipo = tipo;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
