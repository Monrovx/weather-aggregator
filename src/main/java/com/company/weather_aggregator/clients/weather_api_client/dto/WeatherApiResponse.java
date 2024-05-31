package com.company.weather_aggregator.clients.weather_api_client.dto;

public record WeatherApiResponse(
        Location location,
        WeatherApiDays forecast
) {
}
