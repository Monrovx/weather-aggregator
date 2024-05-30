package com.company.weather_aggregator.service;

import com.company.weather_aggregator.dto.DayForecast;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface WeatherService {
    CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForTomorrow(String city);

    CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForWeek(String city);
}
