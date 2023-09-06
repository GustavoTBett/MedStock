package com.app.medStock.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clients extends MasterEntity{
    private String name;
    private String email;
    private Long phone;
    private String cnpj;
    private String zipcode;
    private String address;
    private State state;
}
