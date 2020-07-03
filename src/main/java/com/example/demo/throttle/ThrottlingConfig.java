package com.example.demo.throttle;

public class ThrottlingConfig {

    static final ThrottlingConfig DEFAULT = new ThrottlingConfig(600, 300);

    private int timeFrameInSeconds;
    private int callsCount;

    public ThrottlingConfig(int timeFrameInSeconds, int callsCount) {
        this.timeFrameInSeconds = timeFrameInSeconds;
        this.callsCount = callsCount;
    }

    public int getTimeFrameInSeconds() {
        return timeFrameInSeconds;
    }

    public int getCallsCount() {
        return callsCount;
    }
}
