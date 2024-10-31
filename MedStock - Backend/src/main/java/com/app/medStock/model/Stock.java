package com.app.medStock.model;

import jakarta.persistence.*;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "stock")
public class Stock extends MasterEntity{
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "batch_id")
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
