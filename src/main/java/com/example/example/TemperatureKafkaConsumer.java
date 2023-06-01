package com.example.example;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TemperatureKafkaConsumer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TemperatureKafkaConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "Temperatura", groupId = "group_id")
    public void consumer(TemperatureDto dto) throws Exception {
        detectTemperatureFromTemperatureDto(dto);
        sendAlarm();
    }

    private void detectTemperatureFromTemperatureDto(TemperatureDto dto) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        env.fromElements(dto)
                .filter(new TemperatureFilter())
                .map(new TemperatureDtoToTemperatureAlarmMapper())
                .addSink(new LocalCollectSink());

        env.execute();
    }

    private void sendAlarm() {
        TemperatureAlarmDto alarm = LocalCollectSink.values.iterator().next();
        kafkaTemplate.send("Alarm", alarm);
        LocalCollectSink.values.clear();
    }
}
