package com.app.medStock.model;

import com.app.medStock.enums.Functions;
import com.app.medStock.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends MasterEntity implements PersonalData {

    @Column(name = "name")
    private String name;
    @Column(name = "functions")
    private Functions functions;
    @Column(name = "hiring_date")
    private LocalDate hiringDate;
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private State state;

    @Override
    public ContactInfo getPersonalInfo() {
        ContactInfo contactInfo = new ContactInfo(name, email, phone, cpf, zipcode, address, state);
        return contactInfo;
    }
}
