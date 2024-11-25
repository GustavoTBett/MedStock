package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Item;

public interface VerifyPurchase {
    String responsability(Item item, Batch batch) throws Exception;
    void setProximo(VerifyPurchase verifyPurchase);
}
