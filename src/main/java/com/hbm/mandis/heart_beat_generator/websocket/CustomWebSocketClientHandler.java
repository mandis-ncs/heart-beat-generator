package com.hbm.mandis.heart_beat_generator.websocket;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CompletableFuture;

@Component
public class CustomWebSocketClientHandler extends TextWebSocketHandler {

    @Value("${api.ws.url}")
    private String API_WS_URL;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebSocketClientHandler.class);
    private WebSocketSession session;

    private final StandardWebSocketClient webSocketClient;

    public CustomWebSocketClientHandler(StandardWebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @PostConstruct
    public void connect() {
        logger.info("Attempting to connect to WebSocket Backend...");

        CompletableFuture<WebSocketSession> futureSession = webSocketClient.execute(this, API_WS_URL);

        futureSession.thenAccept(webSocketSession -> {
            this.session = webSocketSession;
            logger.info("Connected to WebSocket Backend!");
        }).exceptionally(throwable -> {
            logger.error("Failed to connect to WebSocket Backend: {}", throwable.getMessage());
            return null;
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.info("Received message from backend: {}", message.getPayload());
    }

    public void sendMeasurement(String measurement) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(measurement));
                logger.info("Measurement sent via WebSocket: {}", measurement);
            } catch (Exception e) {
                logger.error("Error sending measurement: {}", e.getMessage());
            }
        } else {
            logger.warn("WebSocket session is not open!");
        }
    }
}