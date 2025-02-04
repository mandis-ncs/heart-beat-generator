package com.hbm.mandis.heart_beat_generator.utils;

import com.hbm.mandis.heart_beat_generator.domain.models.EcgMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbm.mandis.heart_beat_generator.client.MeasurementSender;
import com.hbm.mandis.heart_beat_generator.service.EcgSimulatorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class MeasurementScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MeasurementScheduler.class);

    private final EcgSimulatorService simulatorService;
    private final MeasurementSender measurementSender;
    private final Random random = new Random();

    public MeasurementScheduler(EcgSimulatorService simulatorService, MeasurementSender measurementSender) {
        this.simulatorService = simulatorService;
        this.measurementSender = measurementSender;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextLong(10, 101)}")
    public void generateAndSendMeasurement() {
        boolean isAnomalous = random.nextDouble() < 0.2; // 20% of change of being an irregularity
        double measurementValue = simulatorService.generateMeasurement(isAnomalous);

        EcgMeasurement measurement = new EcgMeasurement(measurementValue, Instant.now(), "HBM-12345");

        logger.info("Generated measurement: {}mV (Anomalous: {})", measurement.getValue());

        try {
            measurementSender.sendMeasurement(measurement);
            logger.info("Measurement sent successfully!");
        } catch (Exception e) {
            logger.error("Error sending measurement: {}", e.getMessage());
        }
    }
}
