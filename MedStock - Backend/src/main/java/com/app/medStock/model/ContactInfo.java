package com.app.medStock.model;

import com.app.medStock.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gusta
 */
@Getter
@Setter
@AllArgsConstructor
public class ContactInfo {
    private String name;
    private String email;
    private Long phone;
    private String document;
    private String zipcode;
    private String address;
    private State state;
}
