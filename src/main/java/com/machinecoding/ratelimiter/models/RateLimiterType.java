package com.machinecoding.ratelimiter.models;

/**
 * @author salil.mamodiya
 * 25/04/21
 */
public enum RateLimiterType {
    FIXED_WINDOW {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitFixedWindow();
        }
    },
    SLIDING_LOG {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitSlidingLog();
        }
    },
    LEAKY_WINDOW {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitLeakyWindow();
        }
    },
    SLIDING_WINDOW {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitSlidingWindow();
        }
    };

    public abstract <T> T visit(Visitor<T> visitor);

    public interface Visitor<T> {

        T visitFixedWindow();

        T visitSlidingLog();

        T visitLeakyWindow();

        T visitSlidingWindow();
    }
}
