package com.hbm.mandis.heart_beat_generator.exceptions;

import com.hbm.mandis.heart_beat_generator.domain.exceptions.MeasurementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MeasurementException.class)
    public ResponseEntity<String> handleMeasurementException(MeasurementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Measurement error: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
