package com.app.medStock.model;

import com.app.medStock.enums.State;
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
@Table(name = "provider")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider extends MasterEntity{
    private String name;
    private String email;
    private Long phone;
    private String cnpj;
    private String zipcode;
    private String address;
    private State state;
}
