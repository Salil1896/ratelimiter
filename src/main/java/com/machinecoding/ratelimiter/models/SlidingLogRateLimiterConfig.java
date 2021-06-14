package com.machinecoding.ratelimiter.models;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class SlidingLogRateLimiterConfig extends RateLimiterConfig {

    int allowedLimit;
    int windowSize;

    public SlidingLogRateLimiterConfig(int allowedLimit, int windowSize) {
        super(RateLimiterType.SLIDING_LOG);
        this.allowedLimit = allowedLimit;
        this.windowSize = windowSize;
    }

    public int getAllowedLimit() {
        return allowedLimit;
    }

    public int getWindowSize() {
        return windowSize;
    }
}
