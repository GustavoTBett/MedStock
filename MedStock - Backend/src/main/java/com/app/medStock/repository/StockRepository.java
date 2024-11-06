package com.app.medStock.repository;

import com.app.medStock.model.Batch;
import com.app.medStock.model.Product;
import com.app.medStock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductAndBatch(Product product, Batch batch);
}
