package com.app.medStock;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 *
 * @author gustavo
 */
@Component
public class RequestRateLimiter {
    
    private final RateLimiter rateLimiter = RateLimiter.create(9999d);

    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
