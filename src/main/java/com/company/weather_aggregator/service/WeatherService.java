package com.company.weather_aggregator.service;

import com.company.weather_aggregator.dto.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherForToday(String city);

    WeatherResponse getWeatherForWeek(String city);
}
