package com.app.medStock.service;

import com.app.medStock.model.Batch;
import com.app.medStock.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gustavo
 */
@Service
public class BatchService {
    
    @Autowired
    private BatchRepository batchRepository;
    
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElse(null);
    }
}
