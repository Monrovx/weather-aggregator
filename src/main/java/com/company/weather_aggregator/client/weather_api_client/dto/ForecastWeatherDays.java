package com.company.weather_aggregator.client.weather_api_client.dto;

import java.time.LocalDate;

public record ForecastWeatherDays(
        LocalDate date,
        DayWeatherInfo day
) {
}
