package com.app.medStock.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "batch")
public class Batch extends MasterEntity{
    @Column(name = "number")
    private Long number;
    @Column(name = "fabrication_date")
    private LocalDate fabricationDate;
    @Column(name = "valid_date")
    private LocalDate validDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(LocalDate fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Batch() {
    }

    public Batch(Long number, LocalDate fabricationDate, LocalDate validDate, Product product) {
        this.number = number;
        this.fabricationDate = fabricationDate;
        this.validDate = validDate;
        this.product = product;
    }
}
