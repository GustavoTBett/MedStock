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
    USER('U'),
    VIEWER('V'),
    OPERATOR('O');
    
    private final char id;
}
