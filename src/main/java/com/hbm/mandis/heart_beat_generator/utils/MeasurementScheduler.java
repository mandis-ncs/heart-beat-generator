package com.hbm.mandis.heart_beat_generator.utils;

import com.hbm.mandis.heart_beat_generator.domain.exceptions.MeasurementException;
import com.hbm.mandis.heart_beat_generator.domain.models.EcgMeasurement;
import com.hbm.mandis.heart_beat_generator.service.EcgSimulatorService;
import com.hbm.mandis.heart_beat_generator.websocket.MeasurementWebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class MeasurementScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MeasurementScheduler.class);

    private final EcgSimulatorService simulatorService;
    private final MeasurementWebSocketClient webSocketClient;
    private final Random random = new Random();

    public MeasurementScheduler(EcgSimulatorService simulatorService,
                                MeasurementWebSocketClient webSocketClient) {
        this.simulatorService = simulatorService;
        this.webSocketClient = webSocketClient;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextLong(10, 101)}")
    public void generateAndSendMeasurement() {
        boolean isAnomalous = random.nextDouble() < 0.2; // 20% of change of being an irregularity
        double measurementValue = simulatorService.generateMeasurement(isAnomalous);

        EcgMeasurement measurement = new EcgMeasurement(measurementValue, Instant.now(), "550e8400-e29b-41d4-a716-446655440000");

        logger.info("Generated measurement: {}mV", measurement.getValue());

        try {
            webSocketClient.sendMeasurement(measurement);
        } catch (Exception e) {
            throw new MeasurementException("Error sending measurement to websocket: {}", e);
        }
    }
}
