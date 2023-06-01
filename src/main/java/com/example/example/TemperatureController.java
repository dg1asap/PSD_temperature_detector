package com.example.example;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TemperatureController {

    private static final String TOPIC_NAME = "Temperatura";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final Random random = new Random();

    private int counter = 0;

    public void publishTemperature() {
        TemperatureDto dto = TemperatureDto.builder()
                .id(counter++)
                .time(LocalTime.now())
                .temperature(random.nextInt(-30, 30))
                .build();

        kafkaTemplate.send(TOPIC_NAME, dto);
    }
}
