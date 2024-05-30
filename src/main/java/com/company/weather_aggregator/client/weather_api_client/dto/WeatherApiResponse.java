package com.company.weather_aggregator.client.weather_api_client.dto;

public record WeatherApiResponse(
        Location location,
        WeatherApiDays forecast
) {
}
