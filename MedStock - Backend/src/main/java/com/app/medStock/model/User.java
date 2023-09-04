package com.app.medStock.model;

import com.app.medStock.enums.RoleUsers;
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
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends MasterEntity{
    private String name;
    private String email;
    private String password;
    private RoleUsers role;
}
