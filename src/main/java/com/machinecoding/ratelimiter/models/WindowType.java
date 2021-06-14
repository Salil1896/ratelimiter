package com.machinecoding.ratelimiter.models;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public enum WindowType {
    MINUTE {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitMinute();
        }
    },
    HOUR {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitHour();
        }
    },
    DAY {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitDay();
        }
    };

    public abstract <T> T visit(Visitor<T> visitor);

    public interface Visitor<T> {

        T visitMinute();

        T visitHour();

        T visitDay();
    }
}
