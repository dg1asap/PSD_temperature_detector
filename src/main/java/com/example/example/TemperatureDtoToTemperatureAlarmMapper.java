package com.example.example;

import org.apache.flink.api.common.functions.MapFunction;

public class TemperatureDtoToTemperatureAlarmMapper implements MapFunction<TemperatureDto, TemperatureAlarmDto> {

    @Override
    public TemperatureAlarmDto map(TemperatureDto temperatureDto) {
        return TemperatureAlarmDto.builder()
                .temperature(temperatureDto.getTemperature())
                .time(temperatureDto.getTime())
                .build();
    }
}
