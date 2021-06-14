package com.machinecoding.ratelimiter.service;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public interface RateLimiterStrategyService {

    boolean isRequestAllowed(String resourceId);

    boolean isRequestAllowed(String userId, String resourceId);
}
