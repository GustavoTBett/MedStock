package com.app.medStock.repository;

import com.app.medStock.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gustavo
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
}
