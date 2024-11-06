package com.app.medStock.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 *
 * @author gusta
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch extends MasterEntity{
    @Column(name = "number")
    private Long number;
    @Column(name = "fabrication_date")
    private LocalDate fabricationDate;
    @Column(name = "valid_date")
    private LocalDate validDate;
}
