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
public class Provider extends MasterEntity{
    private String name;
    private String email;
    private Long phone;
    private String cnpj;
    private String zipcode;
    private String address;
    private State state;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Provider() {
    }

    public Provider(String name, String email, Long phone, String cnpj, String zipcode, String address, State state) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnpj = cnpj;
        this.zipcode = zipcode;
        this.address = address;
        this.state = state;
    }
}
