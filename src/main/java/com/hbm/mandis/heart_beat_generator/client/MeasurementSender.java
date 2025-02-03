package com.hbm.mandis.heart_beat_generator.client;

import com.hbm.mandis.heart_beat_generator.domain.exceptions.MeasurementException;
import com.hbm.mandis.heart_beat_generator.domain.models.EcgMeasurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MeasurementSender {

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMeasurement(EcgMeasurement measurement) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, measurement, String.class);

            if (!response.getStatusCode().is2xxSuccessful())
                throw new MeasurementException("Failed to send measurement: " + measurement.getValue()
                        + "mV - Status: " + response.getStatusCode());

        } catch (Exception e) {
            throw new MeasurementException("Error while sending measurement", e);
        }
    }
}
