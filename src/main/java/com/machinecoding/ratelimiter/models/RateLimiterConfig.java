package com.machinecoding.ratelimiter.models;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public abstract class RateLimiterConfig {
    private RateLimiterType type;

    public RateLimiterConfig(RateLimiterType type) {
        this.type = type;
    }

    public RateLimiterType getType() {
        return type;
    }
}
