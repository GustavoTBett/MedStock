package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Item;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.StockRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class VerifyValue implements VerifyPurchase {

    private VerifyPurchase verifyPurchase;

    @Override
    public String responsability(Item item, Batch batch) throws Exception {
        BigDecimal finalValue = item.getFinalPrice();
        Double valueOneProduct = item.getPrice().doubleValue() + (item.getPrice().doubleValue() * (item.getFees() / 100)) - (item.getPrice().doubleValue() * (item.getDiscount() / 100));
        Double valueTotal = valueOneProduct * item.getQuantity();

        if (finalValue.doubleValue() == valueTotal) {
            return "Valor correto";
        } else if (this.verifyPurchase != null) {
            return this.verifyPurchase.responsability(item, batch);
        }

        return "Valor incorreto";
    }

    @Override
    public void setProximo(VerifyPurchase verifyPurchase) {
        this.verifyPurchase = verifyPurchase;
    }
}
