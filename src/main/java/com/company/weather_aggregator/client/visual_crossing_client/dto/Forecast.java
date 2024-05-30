package com.company.weather_aggregator.client.visual_crossing_client.dto;

import java.util.List;

public record Forecast(
        List<WeatherInfo> values,
        String address
) {
}
