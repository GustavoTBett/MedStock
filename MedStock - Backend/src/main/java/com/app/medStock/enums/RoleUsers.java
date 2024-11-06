package com.app.medStock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author gusta
 */
@Getter
@AllArgsConstructor
public enum RoleUsers {
    ADMIN('A'),
    USER('U');
    
    private final char id;
}
