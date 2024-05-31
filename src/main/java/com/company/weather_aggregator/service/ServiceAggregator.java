package com.company.weather_aggregator.service;

import com.company.weather_aggregator.dto.WeatherResponseDto;

public interface ServiceAggregator {

    public WeatherResponseDto getAggregatedInfoForTomorrow(String city);

    public WeatherResponseDto getAggregatedInfoForWeek(String city);
}
