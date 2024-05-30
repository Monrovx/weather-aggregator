package com.company.weather_aggregator.client.weather_api_client.dto;

import java.util.List;

public record WeatherApiDays(
        List<ForecastWeatherDays> forecastday
) {
}
