package com.example.example;

import org.apache.flink.api.common.functions.FilterFunction;

public class TemperatureFilter implements FilterFunction<TemperatureDto> {

    @Override
    public boolean filter(TemperatureDto dto) {
        return dto.getTemperature() <= 0;
    }
}
