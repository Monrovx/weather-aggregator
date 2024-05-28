package com.company.weather_aggregator.dto;

import java.math.BigDecimal;

public class WeatherResponse {
    private String city;
    private BigDecimal temperature;
    private String weather;
    private String windSpeed;
    private String sourceName;

    public WeatherResponse(String city, BigDecimal temperature, String weather, String windSpeed, String sourceName) {
        this.city = city;
        this.temperature = temperature;
        this.weather = weather;
        this.windSpeed = windSpeed;
        this.sourceName = sourceName;
    }

    public String getCity() {
        return city;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getSourceName() {
        return sourceName;
    }
}
