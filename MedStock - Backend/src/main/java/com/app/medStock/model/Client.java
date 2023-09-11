package com.app.medStock.model;

import com.app.medStock.enums.State;

/**
 *
 * @author gusta
 */
public class Client extends MasterEntity implements PersonalData {
    private String name;
    private String email;
    private Long phone;
    private String cpf;
    private String zipcode;
    private String address;
    private State state;

    public Client() {
    }

    public Client(String name, String email, Long phone, String cpf, String zipcode, String address, State state) {
        this.name = name;
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
