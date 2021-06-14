package com.machinecoding.ratelimiter.service;

import com.machinecoding.ratelimiter.models.FixedWindowRateLimiterConfig;
import com.machinecoding.ratelimiter.models.RateLimiterConfig;
import com.machinecoding.ratelimiter.models.RateLimiterType;
import com.machinecoding.ratelimiter.models.SlidingLogRateLimiterConfig;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class RateLimiterServiceImpl implements RateLimiterService {

    private RateLimiterStrategyService rateLimiterStrategyService;

    public RateLimiterServiceImpl(RateLimiterConfig rateLimiterConfig) {
        rateLimiterStrategyService = getRateLimiterStrategyService(rateLimiterConfig);
    }

    public boolean isRequestAllowed(String resourceId) {
        return rateLimiterStrategyService.isRequestAllowed(resourceId);
    }

    public boolean isRequestAllowed(String userId, String resourceId) {
        return rateLimiterStrategyService.isRequestAllowed(userId, resourceId);
    }

    private RateLimiterStrategyService getRateLimiterStrategyService(RateLimiterConfig rateLimiterConfig) {
        return rateLimiterConfig.getType().visit(new RateLimiterType.Visitor<RateLimiterStrategyService>() {
            @Override
            public RateLimiterStrategyService visitFixedWindow() {
                FixedWindowRateLimiterConfig config = (FixedWindowRateLimiterConfig) rateLimiterConfig;
                return new FixedWindowStrategyService(config.getWindowType(), config.getAllowedLimit());
            }

            @Override
            public RateLimiterStrategyService visitLeakyWindow() {
                return null;
            }

            @Override
            public RateLimiterStrategyService visitSlidingLog() {
                SlidingLogRateLimiterConfig config = (SlidingLogRateLimiterConfig) rateLimiterConfig;
                return new SlidingWindowStrategyService(config.getWindowSize(), config.getAllowedLimit());
            }

            @Override
            public RateLimiterStrategyService visitSlidingWindow() {
                return null;
            }
        });
    }

}
