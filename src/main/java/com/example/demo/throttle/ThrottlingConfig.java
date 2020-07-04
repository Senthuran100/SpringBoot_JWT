package com.example.demo.throttle;

public class ThrottlingConfig {

    static final ThrottlingConfig DEFAULT = new ThrottlingConfig(600, 300, "Sample");

    private int timeFrameInSeconds;
    private int callsCount;
    private String header;

    public ThrottlingConfig(int timeFrameInSeconds, int callsCount, String header) {
        this.timeFrameInSeconds = timeFrameInSeconds;
        this.callsCount = callsCount;
        this.header = header;
    }

    public int getTimeFrameInSeconds() {
        return timeFrameInSeconds;
    }

    public int getCallsCount() {
        return callsCount;
    }

    public String getHeader() {
        return header;
    }
}
