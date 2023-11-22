package com.app.medStock.model;

import com.app.medStock.enums.State;

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
@Table(name = "provider")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider extends MasterEntity implements PersonalData{
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private State state;

    @Override
    public ContactInfo getPersonalInfo() {
        ContactInfo contactInfo = new ContactInfo(name, email, phone, cnpj, zipcode, address, state);
        return contactInfo;
    }
}
