package com.app.medStock.model;

import com.app.medStock.enums.Functions;
import com.app.medStock.enums.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "employee")
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

    public Employee() {
    }

    public Employee(String name, Functions functions, LocalDate hiringDate, BigDecimal salary, String email, Long phone, String cpf, String zipcode, String address, State state) {
        this.name = name;
        this.functions = functions;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.zipcode = zipcode;
        this.address = address;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Functions getFunctions() {
        return functions;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public ContactInfo getPersonalInfo() {
        ContactInfo contactInfo = new ContactInfo(name, email, phone, cpf, zipcode, address, state);
        return contactInfo;
    }
}
