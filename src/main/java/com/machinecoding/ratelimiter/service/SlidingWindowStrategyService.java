package com.machinecoding.ratelimiter.service;

import org.joda.time.DateTime;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class SlidingWindowStrategyService implements RateLimiterStrategyService {

    private Map<String, Map<String, Deque<Long>>> userResourceWiseAccessMap;
    private Map<String, Deque<Long>> userWiseAccess;
    private long windowSize;
    private int allowedLimit;

    public SlidingWindowStrategyService(int windowSize, int allowedLimit) {
        this.allowedLimit = allowedLimit;
        this.windowSize = windowSize;

        userResourceWiseAccessMap = new HashMap<>();
        userWiseAccess = new HashMap<>();
    }

    public boolean isRequestAllowed(String userId, String resourceId) {

        Deque<Long> deque;
        Long now = DateTime.now().getMillis();
        if (userResourceWiseAccessMap.containsKey(userId) && userResourceWiseAccessMap.get(userId).containsKey(resourceId)) {
            deque = userResourceWiseAccessMap.get(userId).get(resourceId);
            while (deque.size() > 0) {
                Long rTime = deque.getFirst();
                if ((now - rTime) > windowSize) {
                    deque.removeFirst();
                } else break;
            }
            deque.addLast(now);
        } else {
            deque = new LinkedList<>();
            deque.addLast(now);
            userResourceWiseAccessMap.put(userId, new HashMap<>());
            userResourceWiseAccessMap.get(userId).put(resourceId, deque);
        }

        if (deque.size() > allowedLimit) return false;
        return true;
    }


    public boolean isRequestAllowed(String resourceId) {

        Deque<Long> deque;
        Long now = DateTime.now().getMillis();
        if (userWiseAccess.containsKey(resourceId)) {
            deque = userWiseAccess.get(resourceId);
            while (deque.size() > 0) {
                Long rTime = deque.getFirst();
                if ((now - rTime) > windowSize) {
                    deque.removeFirst();
                } else break;
            }
            deque.addLast(now);
        } else {
            deque = new LinkedList<>();
            deque.addLast(now);
            userWiseAccess.put(resourceId, deque);
        }

        if (deque.size() > allowedLimit) return false;
        return true;
    }

}
