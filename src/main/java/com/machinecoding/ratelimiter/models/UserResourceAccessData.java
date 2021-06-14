package com.machinecoding.ratelimiter.models;

import org.joda.time.DateTime;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public class UserResourceAccessData {
    private Integer count;
    private DateTime startTime;

    public UserResourceAccessData(Integer count, DateTime startTime) {
        this.count = count;
        this.startTime = startTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }
}
