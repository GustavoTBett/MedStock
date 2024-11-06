package com.app.medStock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 *
 * @author gusta
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
@Getter
@Setter
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
