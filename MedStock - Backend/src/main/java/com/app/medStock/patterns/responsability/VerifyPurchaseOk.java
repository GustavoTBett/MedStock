package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Item;
import com.app.medStock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyPurchaseOk {

    @Autowired
    private StockRepository stockRepository;

    public String verifyStock(Item item, Batch batch) throws Exception {
        VerifyValue verifyValue = new VerifyValue();
        ExistStock existStock = new ExistStock(stockRepository);

        existStock.setProximo(verifyValue);

        String verify = existStock.responsability(item, batch);

        return verify;
    }
}
