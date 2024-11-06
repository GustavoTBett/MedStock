package com.app.medStock.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author gusta
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends MasterEntity{
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;
    @Column(name = "quantity")
    private Long quantity;
}
