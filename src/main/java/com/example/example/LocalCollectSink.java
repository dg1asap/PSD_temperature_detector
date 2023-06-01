package com.example.example;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LocalCollectSink implements SinkFunction<TemperatureAlarmDto> {

    protected static final Set<TemperatureAlarmDto> values = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void invoke(TemperatureAlarmDto dto, Context context) {
        values.add(dto);
    }
}
