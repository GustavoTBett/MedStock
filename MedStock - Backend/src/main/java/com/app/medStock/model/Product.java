package com.app.medStock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends MasterEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "code")
    private Long code;
    @Column(name = "category")
    private String category;
    @Column(name = "producer")
    private String producer;
}
