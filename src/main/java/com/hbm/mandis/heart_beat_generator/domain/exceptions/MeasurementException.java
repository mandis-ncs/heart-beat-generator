package com.hbm.mandis.heart_beat_generator.domain.exceptions;

public class MeasurementException extends RuntimeException {

    public MeasurementException(String message) {
        super(message);
    }

    public MeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}
