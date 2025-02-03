package com.hbm.mandis.heart_beat_generator.utils;

import com.hbm.mandis.heart_beat_generator.client.MeasurementSender;
import com.hbm.mandis.heart_beat_generator.service.EcgSimulatorService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MeasurementScheduler {

    private final EcgSimulatorService simulatorService;
    private final MeasurementSender measurementSender;
    private final Random random = new Random();

    public MeasurementScheduler(EcgSimulatorService simulatorService, MeasurementSender measurementSender) {
        this.simulatorService = simulatorService;
        this.measurementSender = measurementSender;
    }
}
