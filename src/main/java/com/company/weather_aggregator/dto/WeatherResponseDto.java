package com.company.weather_aggregator.dto;

import java.util.List;
import java.util.Map;

public record WeatherResponseDto(
        Map<String, List<DayForecast>> response
) {
}
