package com.app.medStock.dto.item;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.model.Item;
import com.app.medStock.model.Product;

import java.math.BigDecimal;

/**
 *
 * @author gustavo
 */
public class ItemDto extends MasterEntityDto {
    private Product produto;
    private Long quantidade;
    private BigDecimal preco;
    private Double juros;
    private Double desconto;

    public ItemDto(Item item) {
        this.setId(item.getId());
        this.setCriado(item.getCreatedAt());
        this.setVersao(item.getVersion());
        this.produto = item.getProduct();
        this.quantidade = item.getQuantity();
        this.preco = item.getPrice();
        this.juros = item.getFees();
        this.desconto = item.getDiscount();
    }
    
}
