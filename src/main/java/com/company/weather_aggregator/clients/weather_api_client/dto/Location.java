package com.company.weather_aggregator.clients.weather_api_client.dto;

public record Location(
        String name,
        String region,
        String country
) {
    @Override
    public String toString() {
        return name + ", " +
                region + ", " +
                country;
    }
}
