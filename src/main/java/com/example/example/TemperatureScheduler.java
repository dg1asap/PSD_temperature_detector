package com.example.example;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class TemperatureScheduler {

    private static final int MIN_DELAY = 5;

    private static final int MAX_DELAY = 15;

    private final TemperatureController temperatureController;

    private final Random random = new Random();

    @Scheduled(fixedDelay = 1)
    public void executeTask() {
        waitRandomTimeInterval();
        temperatureController.publishTemperature();
    }

    private void waitRandomTimeInterval() {
        int delay = random.nextInt(MAX_DELAY) + MIN_DELAY;
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
