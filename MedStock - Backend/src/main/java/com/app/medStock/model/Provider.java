package com.app.medStock.model;

import com.app.medStock.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "provider")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provider extends MasterEntity implements PersonalData {

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
