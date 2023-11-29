package com.app.medStock.repository;

import com.app.medStock.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, CustomQuerydslPredicateExecutor<Item> {

}
