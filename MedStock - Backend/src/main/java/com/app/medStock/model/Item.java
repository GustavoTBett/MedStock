package com.app.medStock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends MasterEntity{
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "fees")
    private Double fees;
    @Column(name = "discount")
    private Double discount;
    
    public BigDecimal getFinalPrice() {
        BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
        Double jurosCalculado = price.doubleValue() * (fees / 100);
        Double descontoCalculado = price.doubleValue() * (discount / 100);
        BigDecimal diferenca = BigDecimal.valueOf(jurosCalculado - descontoCalculado);
        return total.add(diferenca);
    }
}
