package com.machinecoding.ratelimiter.service;

import com.machinecoding.ratelimiter.models.WindowType;
import com.machinecoding.ratelimiter.models.UserResourceAccessData;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class FixedWindowStrategyService implements RateLimiterStrategyService {

    private Map<String, Map<String, UserResourceAccessData>> userResourceWiseAccessMap;
    private Map<String, UserResourceAccessData> userWiseAccessMap;
    private WindowType windowType;
    private int allowedLimit;

    public FixedWindowStrategyService(WindowType windowType, int allowedLimit) {
        this.windowType = windowType;
        this.allowedLimit = allowedLimit;

        userResourceWiseAccessMap = new HashMap<>();
        userWiseAccessMap = new HashMap<>();
    }

    public boolean isRequestAllowed(String userId, String resourceId) {
        DateTime originTime = originDateTime(windowType);

        UserResourceAccessData accessData;
        if (userResourceWiseAccessMap.containsKey(userId) && userResourceWiseAccessMap.get(userId).containsKey(resourceId)) {
            accessData = userResourceWiseAccessMap.get(userId).get(resourceId);

            if (accessData.getStartTime().isBefore(originTime)) {
                accessData.setStartTime(originTime);
                accessData.setCount(1);
            } else {
                accessData.setCount(accessData.getCount() + 1);
            }
        } else {
            accessData = new UserResourceAccessData(1, originTime);
            userResourceWiseAccessMap.put(userId, new HashMap<>());
            userResourceWiseAccessMap.get(userId).put(resourceId, accessData);
        }

        if (accessData.getCount() > allowedLimit) return false;
        return true;
    }

    public boolean isRequestAllowed(String userId) {
        DateTime originTime = originDateTime(windowType);

        UserResourceAccessData accessData;
        if (userWiseAccessMap.containsKey(userId)) {
            accessData = userWiseAccessMap.get(userId);

            if (accessData.getStartTime().isBefore(originTime)) {
                accessData.setStartTime(originTime);
                accessData.setCount(1);
            } else {
                accessData.setCount(accessData.getCount() + 1);
                userWiseAccessMap.put(userId, accessData);
            }
        } else {
            accessData = new UserResourceAccessData(1, originTime);
            userWiseAccessMap.put(userId, accessData);
        }

        if (accessData.getCount() > allowedLimit) return false;
        return true;
    }

    private DateTime originDateTime(WindowType windowType) {
        DateTime now = DateTime.now();
        return windowType.visit(new WindowType.Visitor<DateTime>() {
            @Override
            public DateTime visitDay() {
                return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0, 0);
            }

            @Override
            public DateTime visitHour() {
                return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), 0, 0, 0);
            }

            @Override
            public DateTime visitMinute() {
                return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now.getMinuteOfHour(), 0, 0);
            }
        });
    }

}
