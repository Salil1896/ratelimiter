package com.machinecoding.ratelimiter.service;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public interface RateLimiterService {

    boolean isRequestAllowed(String userId);

    boolean isRequestAllowed(String userId, String resourceId);
}
