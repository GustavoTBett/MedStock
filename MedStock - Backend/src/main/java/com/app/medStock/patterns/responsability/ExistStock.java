package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Item;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.StockRepository;

import java.util.Optional;

public class ExistStock implements VerifyPurchase {

    private StockRepository stockRepository;
    private VerifyPurchase verifyPurchase;

    public ExistStock(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public String responsability(Item item, Batch batch) throws Exception {
        Optional<Stock> optionalStock = stockRepository.findByProductAndBatch(item.getProduct(), batch);

        if (optionalStock.isEmpty()) {
            throw new Exception();
        }

        long realQuantity = optionalStock.get().getQuantity();

        if (item.getQuantity() <= realQuantity) {
            return this.verifyPurchase.responsability(item, batch);
        }

        return "Estoque insuficiente";
    }

    @Override
    public void setProximo(VerifyPurchase verifyPurchase) {
        this.verifyPurchase = verifyPurchase;
    }
}
