package com.app.medStock.model;

/**
 *
 * @author gusta
 */
//@Entity
//@Table(name = "product")
public class Product extends MasterEntity{
    private String name;
    private String description;
    private Long code;
    private String category;
    private String producer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Product() {
    }

    public Product(String name, String description, Long code, String category, String producer) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.producer = producer;
    }
}
