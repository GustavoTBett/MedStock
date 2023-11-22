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
@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends MasterEntity implements PersonalData {
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "adress")
    private String address;
    @Column(name = "state")
    private State state;

    @Override
    public ContactInfo getPersonalInfo() {
        ContactInfo contactInfo = new ContactInfo(name, email, phone, cpf, zipcode, address, state);
        return contactInfo;
    }
}
