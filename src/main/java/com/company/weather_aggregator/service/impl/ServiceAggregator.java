package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServiceAggregator {
    @Autowired
    WeatherApiService weatherApiService;
    @Autowired
    VisualCrossingService visualCrossingService;

    public WeatherResponseDto getAggregatedInfoForOneDay(String city) {
        Map.Entry<String, List<DayForecast>> weatherApiResponse = weatherApiService.getWeatherForToday(city);
        Map.Entry<String, List<DayForecast>> visualCrossingResponse = visualCrossingService.getWeatherForToday(city);
        Map<String, List<DayForecast>> aggregatedResponse = Map.ofEntries(weatherApiResponse, visualCrossingResponse);
        return new WeatherResponseDto(aggregatedResponse);
    }

    public WeatherResponseDto getAggregatedInfoForWeek(String city) {
        Map.Entry<String, List<DayForecast>> weatherApiResponse = weatherApiService.getWeatherForWeek(city);
        Map.Entry<String, List<DayForecast>> visualCrossingResponse = visualCrossingService.getWeatherForWeek(city);
        Map<String, List<DayForecast>> aggregatedResponse = Map.ofEntries(weatherApiResponse, visualCrossingResponse);
        return new WeatherResponseDto(aggregatedResponse);
    }
}
