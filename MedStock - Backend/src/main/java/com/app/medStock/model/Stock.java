package com.app.medStock.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "stock")
public class Stock extends MasterEntity{
    @Column(name = "product")
    private Product product;
    @Column(name = "batch")
    private Batch batch;
    @Column(name = "quantity")
    private Long quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Stock() {
    }

    public Stock(Product product, Batch batch, Long quantity) {
        this.product = product;
        this.batch = batch;
        this.quantity = quantity;
    }
}
