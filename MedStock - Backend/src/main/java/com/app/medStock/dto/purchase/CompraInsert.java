package com.app.medStock.dto.purchase;

import com.app.medStock.enums.OrderType;
import com.app.medStock.model.Item;
import com.app.medStock.model.Provider;
import com.app.medStock.model.Purchase;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class CompraInsert {
    private LocalDateTime dataCompra;
    private Long fornecedorId;
    private OrderType tipo;
    private List<Long> itensId;
    
    public CompraInsert(Purchase purchase) {
        this.dataCompra = purchase.getPurchaseDate();
        this.fornecedorId = purchase.getProvider().getId();
        this.tipo = purchase.getOrderType();
        this.itensId = purchase.getItens().stream().map(x -> x.getId()).toList();
    }

    public CompraInsert() {
    }

    public CompraInsert(LocalDateTime dataCompra, Long fornecedorId, OrderType tipo, List<Long> itensId) {
        this.dataCompra = dataCompra;
        this.fornecedorId = fornecedorId;
        this.tipo = tipo;
        this.itensId = itensId;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
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

    
}
