package com.hbm.mandis.heart_beat_generator.domain.models;

import java.time.Instant;

public class EcgMeasurement {
    private double value;
    private Instant timestamp;
    private String deviceId;

    public EcgMeasurement(double value, Instant timestamp, String deviceId) {
        this.value = value;
        this.timestamp = timestamp;
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }
}
