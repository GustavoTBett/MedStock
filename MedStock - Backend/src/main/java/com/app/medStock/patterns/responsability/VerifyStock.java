package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;

public interface VerifyStock {
    long verifyStock(Product product, Batch batch) throws Exception;
    void setProximo(VerifyStock verifyStock);
}
