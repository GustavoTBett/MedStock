package com.app.medStock.patterns.builder;

import com.app.medStock.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ActionGenerator {
    protected LocalDateTime date;
    protected OrderType orderType;

}
