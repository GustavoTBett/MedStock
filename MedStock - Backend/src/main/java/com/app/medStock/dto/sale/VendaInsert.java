package com.app.medStock.dto.sale;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Sale;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class VendaInsert {
    private LocalDateTime dataVenda;
    private Long clientId;
    private OrderType tipo;
    private List<Long> itensId;
    private Long funcionarioId;
    
    public VendaInsert(Sale sale) {
        this.dataVenda = sale.getSaleDate();
        this.clientId = sale.getClient().getId();
        this.tipo = sale.getType();
        this.itensId = sale.getItens().stream().map(x -> x.getId()).toList();
        this.funcionarioId = sale.getEmployee().getId();
    }

    public VendaInsert() {
    }

    public VendaInsert(LocalDateTime dataVenda, Long clientId, OrderType tipo, List<Long> itensId, Long funcionarioId) {
        this.dataVenda = dataVenda;
        this.clientId = clientId;
        this.tipo = tipo;
        this.itensId = itensId;
        this.funcionarioId = funcionarioId;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public OrderType getTipo() {
        return tipo;
    }

    public void setTipo(OrderType tipo) {
        this.tipo = tipo;
    }

    public List<Long> getItensId() {
        return itensId;
    }

    public void setItensId(List<Long> itensId) {
        this.itensId = itensId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    
}
