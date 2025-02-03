package com.hbm.mandis.heart_beat_generator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EcgSimulatorService {

    private static final double BASELINE_MIN = 0.8;
    private static final double BASELINE_MAX = 1.2;
    private static final double ANOMALY_MIN = 1.8;
    private static final double ANOMALY_MAX = 2.5;

    private final Random random = new Random();

    private final Logger logger = LoggerFactory.getLogger(EcgSimulatorService.class);

    public double generateMeasurement(boolean isAnomalous) {
        logger.info("Generating random measurement");
        if (isAnomalous) {
            return ANOMALY_MIN + (ANOMALY_MAX - ANOMALY_MIN) * random.nextDouble();
        } else {
            return BASELINE_MIN + (BASELINE_MAX - BASELINE_MIN) * random.nextDouble();
        }
    }

    public long generateRandomInterval() {
        return ThreadLocalRandom.current().nextLong(10, 101);
    }

}
