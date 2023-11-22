package com.app.medStock.repository;

import com.querydsl.core.types.Predicate;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author gustavo
 */
public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {

    @Override
    List<T> findAll(Predicate predicate);
}