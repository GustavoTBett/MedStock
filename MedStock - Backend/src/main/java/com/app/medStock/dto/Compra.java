package com.app.medStock.dto;

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
public class Compra extends MasterEntityDto {
    private LocalDateTime dataCompra;
    private Provider fornecedor;
    private OrderType tipo;
    private List<Item> itens;
    
    public Compra(Purchase purchase) {
        this.setId(purchase.getId());
        this.setCriado(purchase.getCreated());
        this.setVersao(purchase.getVersion());
        this.dataCompra = purchase.getPurchaseDate();
        this.fornecedor = purchase.getProvider();
        this.tipo = purchase.getOrderType();
        this.itens = purchase.getItens();
    }

    public Compra() {
    }

    public Compra(LocalDateTime dataCompra, Provider fornecedor, OrderType tipo, List<Item> itens) {
        this.dataCompra = dataCompra;
        this.fornecedor = fornecedor;
        this.tipo = tipo;
        this.itens = itens;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Provider getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Provider fornecedor) {
        this.fornecedor = fornecedor;
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
    
    
}
