package com.company.weather_aggregator.services;

import com.company.weather_aggregator.dto.WeatherResponseDto;

public interface ServiceAggregator {

    WeatherResponseDto getAggregatedInfoForToday(String city);

    WeatherResponseDto getAggregatedInfoForWeek(String city);
}
