package com.company.weather_aggregator.service;

import com.company.weather_aggregator.dto.DayForecast;

import java.util.List;
import java.util.Map;

public interface WeatherService {
    Map.Entry<String, List<DayForecast>> getWeatherForToday(String city);

    Map.Entry<String, List<DayForecast>> getWeatherForWeek(String city);
}
