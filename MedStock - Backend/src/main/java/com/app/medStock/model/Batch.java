package com.app.medStock.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
