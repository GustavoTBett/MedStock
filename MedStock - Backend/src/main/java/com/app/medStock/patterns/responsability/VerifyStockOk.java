package com.app.medStock.patterns.responsability;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import com.app.medStock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyStockOk {

    @Autowired
    private StockRepository stockRepository;

    long verifyStock(Product product, Batch batch) throws Exception {
        Order2Product order2Product = new Order2Product(stockRepository);
        Order4Product order4Product = new Order4Product(stockRepository);
        Order6Product order6Product = new Order6Product(stockRepository);

        order6Product.setProximo(order4Product);
        order4Product.setProximo(order2Product);

        long quantity = order6Product.verifyStock(product, batch);

        return quantity;
    }
}
