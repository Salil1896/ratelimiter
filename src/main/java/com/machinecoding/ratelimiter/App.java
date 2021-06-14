package com.machinecoding.ratelimiter;


import com.machinecoding.ratelimiter.models.FixedWindowRateLimiterConfig;
import com.machinecoding.ratelimiter.models.WindowType;
import com.machinecoding.ratelimiter.service.RateLimiterService;
import com.machinecoding.ratelimiter.service.RateLimiterServiceImpl;

public class App {

    public static void main(String args[]) {

        FixedWindowRateLimiterConfig config = new FixedWindowRateLimiterConfig(2, WindowType.MINUTE);
        RateLimiterService rateLimiterService = new RateLimiterServiceImpl(config);

        System.out.println("User Level Access: ");
        System.out.println(rateLimiterService.isRequestAllowed("user1"));
        System.out.println(rateLimiterService.isRequestAllowed("user1"));
        System.out.println(rateLimiterService.isRequestAllowed("user1"));

        //Thread.sleep(60000);

        System.out.println("User and Resource Level Access: ");
        System.out.println(rateLimiterService.isRequestAllowed("user1", "api1"));
        System.out.println(rateLimiterService.isRequestAllowed("user1", "api1"));
        System.out.println(rateLimiterService.isRequestAllowed("user1", "api1"));

    }

}
