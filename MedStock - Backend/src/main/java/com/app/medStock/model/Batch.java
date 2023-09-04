package com.app.medStock.model;

import java.time.LocalDate;
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
@Table(name = "batch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch extends MasterEntity{
    private Long number;
    private LocalDate fabricationDate;
    private LocalDate validDate;
    private Product product;
}
