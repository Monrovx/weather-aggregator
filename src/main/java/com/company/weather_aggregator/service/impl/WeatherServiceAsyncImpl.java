package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.dto.WeatherResponse;
import com.company.weather_aggregator.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceAsyncImpl implements WeatherService {
    @Override
    public WeatherResponse getWeatherForToday(String city) {

        return null;
    }

    @Override
    public WeatherResponse getWeatherForWeek(String city) {
        return null;
    }
}
