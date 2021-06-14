package com.machinecoding.ratelimiter.models;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class FixedWindowRateLimiterConfig extends RateLimiterConfig {

    int allowedLimit;
    private WindowType windowType;

    public FixedWindowRateLimiterConfig(int allowedLimit, WindowType windowType) {
        super(RateLimiterType.FIXED_WINDOW);
        this.allowedLimit = allowedLimit;
        this.windowType = windowType;
    }

    public int getAllowedLimit() {
        return allowedLimit;
    }

    public WindowType getWindowType() {
        return windowType;
    }
}
