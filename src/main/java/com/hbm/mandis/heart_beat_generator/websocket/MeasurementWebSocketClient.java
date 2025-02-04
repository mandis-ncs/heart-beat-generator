package com.hbm.mandis.heart_beat_generator.websocket;

import com.hbm.mandis.heart_beat_generator.domain.models.EcgMeasurement;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MeasurementWebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(MeasurementWebSocketClient.class);
    private final CustomWebSocketClientHandler webSocketHandler;

    public MeasurementWebSocketClient(CustomWebSocketClientHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostConstruct
    public void init() {
        logger.info("Initializing WebSocket Client...");
        webSocketHandler.connect();
    }

    public void sendMeasurement(EcgMeasurement measurement) {
        webSocketHandler.sendMeasurement(measurement.toString());
    }
}