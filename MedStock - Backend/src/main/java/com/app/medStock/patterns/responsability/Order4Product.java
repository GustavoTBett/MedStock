package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class Order4Product implements VerifyStock{

    private final StockRepository stockRepository;
    private VerifyStock verifyStock;

    public Order4Product(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public long verifyStock(Product product, Batch batch) throws Exception {
        Optional<Stock> optionalStock = stockRepository.findByProductAndBatch(product, batch);
        if (optionalStock.isEmpty()) {
            throw new Exception();
        }
        long realQuantity = optionalStock.get().getQuantity();
        if (4 <= realQuantity) {
            return realQuantity;
        } else {
            return this.verifyStock.verifyStock(product, batch);
        }
    }

    @Override
    public void setProximo(VerifyStock verifyStock) {
        this.verifyStock = verifyStock;
    }
}
