package com.app.medStock.model;

import java.time.LocalDate;

/**
 *
 * @author gusta
 */
//@Entity
//@Table(name = "batch")
public class Batch extends MasterEntity{
    private Long number;
    private LocalDate fabricationDate;
    private LocalDate validDate;
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
