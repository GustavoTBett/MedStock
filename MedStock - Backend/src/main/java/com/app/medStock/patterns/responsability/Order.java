package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import lombok.Data;

@Data
public class Order {
    private Product product;
    private int quantity;
    private Batch batch;
}
