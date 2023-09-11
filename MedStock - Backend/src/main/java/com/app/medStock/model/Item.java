package com.app.medStock.model;

import java.math.BigDecimal;

/**
 *
 * @author gusta
 */
public class Item extends MasterEntity{
    private Product product;
    private Long quantity;
    private BigDecimal price;
    private Double fees;
    private Double discount;

    public Item() {
    }

    public Item(Product product, Long quantity, BigDecimal price, Double fees, Double discount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.fees = fees;
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
        return total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    
}
