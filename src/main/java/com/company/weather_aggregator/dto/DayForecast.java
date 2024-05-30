package com.company.weather_aggregator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DayForecast(
        String city,
        BigDecimal temperature,
        String weather,
        BigDecimal windSpeed,
        LocalDate forecastTime
) {
}
